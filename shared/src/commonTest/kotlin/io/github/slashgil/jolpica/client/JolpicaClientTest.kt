package io.github.slashgil.jolpica.client

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

class JolpicaClientTest {

    private fun createMockClient(responseJson: String, onCheckRequest: (String) -> Unit = {}): JolpicaClient {
        val mockEngine = MockEngine { request ->
            onCheckRequest(request.url.toString())
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
        return JolpicaClient(httpClient = httpClient)
    }

    @Test
    fun testGetDriverStandings() = runTest {
        val json = """
            {
              "MRData": {
                "series": "f1",
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

        var requestedUrl = ""
        val client = createMockClient(json) { requestedUrl = it }

        val response = client.getDriverStandings(year = "2023", limit = 10)

        assertEquals("https://api.jolpi.ca/ergast/f1/2023/driverstandings.json?limit=10", requestedUrl)
        val standing = response.mrData.standingsTable?.standingsLists?.firstOrNull()?.driverStandings?.firstOrNull()
        assertNotNull(standing)
        assertEquals("max_verstappen", standing.driver.driverId)
    }

    @Test
    fun testGetRaceResults() = runTest {
        val json = """
            {
              "MRData": {
                "series": "f1",
                "RaceTable": {
                  "season": "2023",
                  "round": "1",
                  "Races": [
                    {
                      "season": "2023",
                      "round": "1",
                      "raceName": "Bahrain Grand Prix",
                      "Results": [
                        {
                          "position": "1",
                          "positionText": "1",
                          "Driver": {
                            "driverId": "max_verstappen",
                            "givenName": "Max",
                            "familyName": "Verstappen"
                          },
                          "Constructor": {
                            "constructorId": "red_bull",
                            "name": "Red Bull"
                          }
                        }
                      ]
                    }
                  ]
                }
              }
            }
        """.trimIndent()

        var requestedUrl = ""
        val client = createMockClient(json) { requestedUrl = it }

        val response = client.getRaceResults(year = "2023", round = "1")

        assertEquals("https://api.jolpi.ca/ergast/f1/2023/1/results.json", requestedUrl)
        val race = response.mrData.raceTable?.races?.firstOrNull()
        assertNotNull(race)
        assertEquals("Bahrain Grand Prix", race.raceName)
    }

    @Test
    fun testGetSeasons() = runTest {
        val json = """
            {
              "MRData": {
                "series": "f1",
                "SeasonTable": {
                  "Seasons": [
                    { "season": "2023" },
                    { "season": "2024" }
                  ]
                }
              }
            }
        """.trimIndent()

        var requestedUrl = ""
        val client = createMockClient(json) { requestedUrl = it }

        val response = client.getSeasons(limit = 2, offset = 0)

        assertEquals("https://api.jolpi.ca/ergast/f1/seasons.json?limit=2&offset=0", requestedUrl)
        val seasons = response.mrData.seasonTable?.seasons
        assertNotNull(seasons)
        assertEquals(2, seasons.size)
        assertEquals("2023", seasons[0].season)
    }

    @Test
    fun testGetCircuits() = runTest {
        val json = """
            {
              "MRData": {
                "series": "f1",
                "CircuitTable": {
                  "season": "2023",
                  "Circuits": [
                    {
                      "circuitId": "monaco",
                      "circuitName": "Circuit de Monaco"
                    }
                  ]
                }
              }
            }
        """.trimIndent()

        var requestedUrl = ""
        val client = createMockClient(json) { requestedUrl = it }

        val response = client.getCircuits(year = "2023")

        assertEquals("https://api.jolpi.ca/ergast/f1/2023/circuits.json", requestedUrl)
        val circuits = response.mrData.circuitTable?.circuits
        assertNotNull(circuits)
        assertEquals("monaco", circuits.first().circuitId)
    }
}
