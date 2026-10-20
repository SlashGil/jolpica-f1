package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.JolpicaSdk
import io.github.slashgil.jolpica.client.JolpicaClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class UseCaseTest {

    private fun createMockSdk(responseJson: String): JolpicaSdk {
        val mockEngine = MockEngine {
            respond(
                content = responseJson,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
        val client = JolpicaClient(httpClient = httpClient)
        return JolpicaSdk(client = client)
    }

    @Test
    fun testGetDriversUseCase() = runTest {
        val json = """
            {
              "MRData": {
                "series": "f1",
                "limit": "10",
                "offset": "0",
                "total": "1",
                "DriverTable": {
                  "Drivers": [
                    {
                      "driverId": "alonso",
                      "permanentNumber": "14",
                      "givenName": "Fernando",
                      "familyName": "Alonso",
                      "nationality": "Spanish"
                    }
                  ]
                }
              }
            }
        """.trimIndent()

        val sdk = createMockSdk(json)
        val result = sdk.getDrivers(year = "2023")

        assertTrue(result.isSuccess)
        val page = result.getOrNull()
        assertNotNull(page)
        assertEquals(1, page.items.size)
        val driver = page.items.first()
        assertEquals("alonso", driver.driverId)
        assertEquals(14, driver.permanentNumber)
        assertEquals("Fernando Alonso", driver.fullName)
    }

    @Test
    fun testGetDriverStandingsUseCase() = runTest {
        val json = """
            {
              "MRData": {
                "limit": "30",
                "offset": "0",
                "total": "1",
                "StandingsTable": {
                  "season": "2023",
                  "StandingsLists": [
                    {
                      "season": "2023",
                      "round": "22",
                      "DriverStandings": [
                        {
                          "position": "1",
                          "positionText": "1",
                          "points": "575",
                          "wins": "19",
                          "Driver": {
                            "driverId": "max_verstappen",
                            "givenName": "Max",
                            "familyName": "Verstappen"
                          }
                        }
                      ]
                    }
                  ]
                }
              }
            }
        """.trimIndent()

        val sdk = createMockSdk(json)
        val result = sdk.getDriverStandings(year = "2023")

        assertTrue(result.isSuccess)
        val page = result.getOrNull()
        assertNotNull(page)
        assertEquals(1, page.items.size)
        val standing = page.items.first()
        assertEquals(1, standing.position)
        assertEquals(575.0, standing.points)
        assertEquals(19, standing.wins)
        assertEquals("max_verstappen", standing.driver.driverId)
    }
}
