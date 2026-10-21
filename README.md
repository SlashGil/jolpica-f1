# Jolpica F1 Kotlin Multiplatform SDK

[![JitPack](https://jitpack.io/v/slashgil/JolpicaF1.svg)](https://jitpack.io/#slashgil/JolpicaF1)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-blue.svg)](https://kotlinlang.org/docs/multiplatform.html)

A lightweight, pure **Kotlin Multiplatform (KMP)** library wrapping the [Jolpica F1 API](https://jolpi.ca/ergast/) (the modern Ergast Formula 1 REST API replacement). Built with **Ktor 3**, **Kotlinx Serialization**, and **Koin DI**. Includes strongly typed **Domain Entities**, **Clean Architecture Use Cases**, **Result state handling (`Success`, `Error`, `Loading`)**, and **Flow reactive state streams**.

---

## 🚀 Features

- 🏎️ **100% Jolpica / Ergast API Coverage**: Drivers, Constructors, Circuits, Driver & Constructor Standings, Race Calendar, Race Results, Qualifying, Sprint Results, Pit Stops, Lap Times, Finishing Statuses, and Criteria Filtering.
- 🎯 **Domain Entities & Clean Architecture**: Strongly-typed `DriverModel`, `RaceModel`, `CircuitModel`, `ConstructorModel`, `StandingModel`, etc., wrapped in generic `Page<T>` pagination containers.
- 🔄 **UI State & Reactive Flow Streams**: Custom `Result<T>` (`Success`, `Error`, `Loading`) and `.asFlow(...)` Use Case extensions for Jetpack Compose, Compose Multiplatform, StateFlow, and SwiftUI.
- 💉 **Koin Dependency Injection**: Built-in Koin modules (`networkModule`, `repositoryModule`, `useCaseModule`, `jolpicaModule`) and `initKoin` helper.
- 🌐 **Kotlin Multiplatform**: Supports **JVM**, **Android**, and **iOS** (`iosX64`, `iosArm64`, `iosSimulatorArm64`).
- ⚡ **Asynchronous & Non-Blocking**: Fully `suspend`-based API designed for Kotlin Coroutines and Swift `async/await`.

---

## 📦 Installation

### 1. Add JitPack Repository

In your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. Add Dependency

In your `commonMain` or module `build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation("com.github.slashgil.JolpicaF1:shared:v1.0.2")
}
```

---

## 🍏 iOS Integration (Swift & Xcode)

### Option 1: KMP App
If your iOS app is part of a Kotlin Multiplatform codebase, simply include the Maven Central dependency in `commonMain.dependencies` as shown above. Maven Central publishes `iosArm64`, `iosX64`, and `iosSimulatorArm64` targets automatically.

### Option 2: Native Swift / Xcode Project (`XCFramework`)
For native Xcode / SwiftUI projects, you can compile the library into a native `JolpicaKmpSdk.xcframework`:

1. **Build the XCFramework**:
   ```bash
   ./gradlew :shared:assembleJolpicaKmpSdkXCFramework
   ```
2. **Add to Xcode**:
   - Locate the output framework in `shared/build/XCFrameworks/release/JolpicaKmpSdk.xcframework`.
   - Drag it into your Xcode project under **Target > General > Frameworks, Libraries, and Embedded Content**.
   - Set the embedding option to **Embed & Sign**.

### 📱 Swift Usage Example

```swift
import SwiftUI
import JolpicaKmpSdk

class F1ViewModel: ObservableObject {
    private let client = JolpicaClientKt.JolpicaClient()
    
    @Published var driverName: String = ""

    // Swift Async/Await (iOS 15+)
    func fetchStandingsAsync() async throws {
        let response = try await client.getDriverStandings(year: "2024", round: nil, driverId: nil, position: nil, limit: nil, offset: nil)
        if let standings = response.mrData.standingsTable?.standingsLists.first?.driverStandings?.first {
            DispatchQueue.main.async {
                self.driverName = "\(standings.driver.givenName) \(standings.driver.familyName)"
            }
        }
    }
}
```

---

## 💡 Quick Start (Kotlin)

```kotlin
import io.github.slashgil.jolpica.JolpicaSdk
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.onError
import io.github.slashgil.jolpica.domain.model.onSuccess

suspend fun main() {
    val sdk = JolpicaSdk()

    try {
        // Fetch 2024 Driver Standings via UseCase
        val result = sdk.getDriverStandings(year = "2024")
        
        result.onSuccess { page ->
            page.items.forEach { standing ->
                println("${standing.position}. ${standing.driver.fullName} - ${standing.points} pts")
            }
        }.onError { exception ->
            println("Error fetching standings: ${exception.message}")
        }

        // Collect UI State Flow (emits Loading -> Success or Error)
        sdk.getRaceResults.asFlow(year = "2023", driverId = "alonso", grid = "1")
            .collect { state ->
                when (state) {
                    is Result.Loading -> println("Loading race results...")
                    is Result.Success -> println("Fetched ${state.data.items.size} races")
                    is Result.Error -> println("Error: ${state.exception.message}")
                }
            }

    } finally {
        sdk.close()
    }
}
```

---

## 📖 API Reference & Endpoint Matrix

| Method | Supported Filter Criteria & Parameters | Jolpica F1 Endpoint Path Example |
| :--- | :--- | :--- |
| `getDriverStandings` | `year`, `round`, `driverId`, `position`, `limit`, `offset` | `/2023/drivers/verstappen/driverstandings.json` |
| `getConstructorStandings` | `year`, `round`, `constructorId`, `position`, `limit`, `offset` | `/2023/constructors/red_bull/constructorstandings.json` |
| `getRaceResults` | `year`, `round`, `driverId`, `constructorId`, `circuitId`, `grid`, `fastest`, `statusId`, `limit`, `offset` | `/2023/drivers/alonso/grid/1/results.json` |
| `getQualifyingResults` | `year`, `round`, `driverId`, `constructorId`, `limit`, `offset` | `/2023/1/qualifying.json` |
| `getSprintResults` | `year`, `round`, `driverId`, `constructorId`, `limit`, `offset` | `/2023/1/sprint.json` |
| `getSchedule` | `year`, `round`, `circuitId`, `driverId`, `constructorId`, `limit`, `offset` | `/2024.json` |
| `getDrivers` | `year`, `round`, `constructorId`, `circuitId`, `limit`, `offset` | `/2023/constructors/ferrari/drivers.json` |
| `getConstructors` | `year`, `round`, `driverId`, `circuitId`, `limit`, `offset` | `/2023/drivers/hamilton/constructors.json` |
| `getCircuits` | `year`, `round`, `driverId`, `constructorId`, `limit`, `offset` | `/2023/constructors/red_bull/circuits.json` |
| `getSeasons` | `driverId`, `constructorId`, `circuitId`, `limit`, `offset` | `/drivers/hamilton/seasons.json` |
| `getPitStops` | `year`, `round`, `driverId`, `stopNumber`, `limit`, `offset` | `/2023/1/drivers/verstappen/pitstops.json` |
| `getLaps` | `year`, `round`, `lapNumber`, `driverId`, `limit`, `offset` | `/2023/1/laps/5.json` |
| `getStatuses` | `year`, `round`, `driverId`, `constructorId`, `limit`, `offset` | `/2023/drivers/alonso/status.json` |
| `getCustomEndpoint` | `path: String`, `limit`, `offset` | Direct query to any custom path |

---

## 🔧 Customizing the Ktor Client

You can pass a custom Ktor `HttpClient` (for logging, custom proxy, certificate pinning, or testing mocks):

```kotlin
import io.ktor.client.HttpClient
import io.github.slashgil.jolpica.client.JolpicaClient

val customHttpClient = HttpClient {
    // Add custom Ktor plugins here
}

val client = JolpicaClient(httpClient = customHttpClient)
```

---

## 🛠️ Building & Testing Locally

To build the library, run unit tests, or assemble the XCFramework:

```bash
# Build and run tests across all targets
./gradlew :shared:check :shared:build

# Generate XCFramework for iOS/Xcode
./gradlew :shared:assembleJolpicaKmpSdkXCFramework

# Publish to local Maven repository (~/.m2/repository)
./gradlew :shared:publishToMavenLocal
```

---

## 📄 License

This library is published under the **MIT License**. See [LICENSE](LICENSE) for details.
