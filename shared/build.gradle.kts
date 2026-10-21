import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.mavenPublish)
}

group = "io.github.slashgil"
version = "1.0.2"

kotlin {
    jvm()

    val xcf = XCFramework("JolpicaKmpSdk")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "JolpicaKmpSdk"
            isStatic = true
            xcf.add(this)
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
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.test)
        }
    }
}

mavenPublishing {
    coordinates("com.github.slashgil.JolpicaF1", "shared", project.version.toString())

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
                url.set("https://github.com/slashgil")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/slashgil/JolpicaF1.git")
            developerConnection.set("scm:git:ssh://github.com/slashgil/JolpicaF1.git")
            url.set("https://github.com/slashgil/JolpicaF1")
        }
    }
}
