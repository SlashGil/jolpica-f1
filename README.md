# Jolpica F1 Kotlin Multiplatform SDK

[![Maven Central](https://img.shields.io/maven-central/v/io.github.slashgil/shared.svg)](https://central.sonatype.com/search?q=g%3Aio.github.slashgil)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-blue.svg)](https://kotlinlang.org/docs/multiplatform.html)

A lightweight, pure **Kotlin Multiplatform (KMP)** library wrapping the [Jolpica F1 API](https://jolpi.ca/ergast/) (the modern Ergast Formula 1 REST API replacement). Built with **Ktor 3** and **Kotlinx Serialization**.

---

## 🚀 Features

- 🏎️ **Complete Endpoint Coverage**: Drivers, Constructors, Circuits, Driver & Constructor Standings, Race Calendar, Race Results, Qualifying, Sprint Results, Pit Stops, Lap Times, and Finishing Statuses.
- 🌐 **Kotlin Multiplatform**: Supports **JVM**, **Android**, and **iOS** (`iosX64`, `iosArm64`, `iosSimulatorArm64`).
- ⚡ **Asynchronous & Non-Blocking**: Fully `suspend`-based API designed for Kotlin Coroutines.
- 📦 **Zero UI Dependencies**: Pure infrastructure library — easy to integrate into any KMP, Android, iOS, or JVM application.

---

## 📦 Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation("io.github.slashgil:jolpica-kmp-sdk:<latest-version>")
}
```

Or for single-platform JVM / Android projects:

```kotlin
dependencies {
    implementation("io.github.slashgil:jolpica-kmp-sdk:<latest-version>")
}
```

---

## 💡 Quick Start

```kotlin
import io.github.slashgil.jolpica.client.JolpicaClient

suspend fun main() {
    // Instantiate the client (uses default Ktor client configuration)
    val client = JolpicaClient()

    try {
        // Fetch 2024 Driver Standings
        val standingsResponse = client.getDriverStandings(year = "2024")
        val standings = standingsResponse.mrData.standingsTable?.standingsLists?.firstOrNull()?.driverStandings
        
        standings?.forEach { standing ->
            println("${standing.position}. ${standing.driver.givenName} ${standing.driver.familyName} - ${standing.points} pts")
        }

        // Fetch Race Results for Round 1
        val raceResponse = client.getRaceResults(year = "2024", round = "1")
        val race = raceResponse.mrData.raceTable?.races?.firstOrNull()
        println("Race: ${race?.raceName} at ${race?.circuit?.circuitName}")

    } finally {
        client.close()
    }
}
```

---

## 📖 API Reference & Endpoint Matrix

| Method | Parameters | Jolpica F1 Endpoint Path |
| :--- | :--- | :--- |
| `getDriverStandings` | `year: String = "current"`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/driverstandings.json` |
| `getConstructorStandings` | `year: String = "current"`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/constructorstandings.json` |
| `getRaceResults` | `year: String = "current"`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/results.json` |
| `getQualifyingResults` | `year: String = "current"`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/qualifying.json` |
| `getSprintResults` | `year: String = "current"`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/sprint.json` |
| `getSchedule` | `year: String = "current"`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}.json` |
| `getDrivers` | `year: String?`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/drivers.json` |
| `getConstructors` | `year: String?`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/constructors.json` |
| `getCircuits` | `year: String?`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/circuits.json` |
| `getSeasons` | `limit: Int?`, `offset: Int?` | `/seasons.json` |
| `getPitStops` | `year: String`, `round: String`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/pitstops.json` |
| `getLaps` | `year: String`, `round: String`, `lapNumber: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/laps/{lapNumber}.json` |
| `getStatuses` | `year: String?`, `round: String?`, `limit: Int?`, `offset: Int?` | `/{year}/{round}/status.json` |

---

## 🔧 Customizing the Ktor Client

You can pass a custom Ktor `HttpClient` (for logging, custom proxy, certificate pinning, or testing mocks):

```kotlin
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.Logging
import io.github.slashgil.jolpica.client.JolpicaClient
import io.github.slashgil.jolpica.client.createDefaultHttpClient

val customHttpClient = HttpClient {
    // Add custom Ktor plugins here
}

val client = JolpicaClient(httpClient = customHttpClient)
```

---

## 🛠️ Building & Testing Locally

To build the library and run unit tests locally:

```bash
# Build and run tests across all targets
./gradlew :shared:check :shared:build

# Publish to local Maven repository (~/.m2/repository)
./gradlew :shared:publishToMavenLocal
```

---

## 📄 License

This library is published under the **MIT License**. See [LICENSE](LICENSE) for details.
