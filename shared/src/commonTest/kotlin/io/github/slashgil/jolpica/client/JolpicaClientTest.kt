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
    fun testGetRaceResultsWithFilters() = runTest {
        val json = """
            {
              "MRData": {
                "series": "f1",
                "RaceTable": {
                  "season": "2023",
                  "Races": []
                }
              }
            }
        """.trimIndent()

        var requestedUrl = ""
        val client = createMockClient(json) { requestedUrl = it }

        client.getRaceResults(year = "2023", driverId = "alonso", grid = "1")

        assertEquals("https://api.jolpi.ca/ergast/f1/2023/drivers/alonso/grid/1/results.json", requestedUrl)
    }

    @Test
    fun testGetSeasonsWithDriverFilter() = runTest {
        val json = """
            {
              "MRData": {
                "series": "f1",
                "SeasonTable": {
                  "Seasons": [
                    { "season": "2023" }
                  ]
                }
              }
            }
        """.trimIndent()

        var requestedUrl = ""
        val client = createMockClient(json) { requestedUrl = it }

        client.getSeasons(driverId = "hamilton")

        assertEquals("https://api.jolpi.ca/ergast/f1/drivers/hamilton/seasons.json", requestedUrl)
    }

    @Test
    fun testGetCustomEndpoint() = runTest {
        val json = """
            {
              "MRData": {
                "series": "f1"
              }
            }
        """.trimIndent()

        var requestedUrl = ""
        val client = createMockClient(json) { requestedUrl = it }

        client.getCustomEndpoint("2023/drivers/hamilton/results")

        assertEquals("https://api.jolpi.ca/ergast/f1/2023/drivers/hamilton/results.json", requestedUrl)
    }
}
