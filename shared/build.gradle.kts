import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.plugins.signing.SigningExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.serialization)
    `maven-publish`
    signing
}

group = "io.github.slashgil"
version = System.getenv("VERSION_NAME") ?: "1.0.0-SNAPSHOT"

kotlin {
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "JolpicaKmpSdk"
            isStatic = true
        }
    }

    android {
        namespace = "io.github.slashgil.jolpica"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

configure<PublishingExtension> {
    publications.all {
        if (this is MavenPublication) {
            pom {
                name.set("Jolpica F1 KMP SDK")
                description.set("Kotlin Multiplatform SDK for the Jolpica F1 / Ergast API")
                url.set("https://github.com/slashgil/JolpicaF1")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("slashgil")
                        name.set("Slash Gil")
                        email.set("slashgil@users.noreply.github.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/slashgil/JolpicaF1.git")
                    developerConnection.set("scm:git:ssh://github.com/slashgil/JolpicaF1.git")
                    url.set("https://github.com/slashgil/JolpicaF1")
                }
            }
        }
    }

    repositories {
        maven {
            name = "MavenCentral"
            val releasesUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl)
            credentials {
                username = System.getenv("MAVEN_USERNAME") ?: ""
                password = System.getenv("MAVEN_PASSWORD") ?: ""
            }
        }
    }
}

configure<SigningExtension> {
    val signingKey = System.getenv("GPG_PRIVATE_KEY")
    val signingPassword = System.getenv("GPG_PASSPHRASE")
    if (!signingKey.isNullOrEmpty()) {
        useInMemoryPgpKeys(signingKey, signingPassword)
        val publishing = extensions.getByType(PublishingExtension::class.java)
        sign(publishing.publications)
    }
}
