package io.github.slashgil.jolpica.model

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ModelSerializationTest {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Test
    fun testDriverStandingsResponseDeserialization() {
        val sampleJson = """
            {
              "MRData": {
                "xmlns": "http://ergast.com/mrd/1.5",
                "series": "f1",
                "url": "https://api.jolpi.ca/ergast/f1/2023/driverstandings/",
                "limit": "30",
                "offset": "0",
                "total": "22",
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
                            "permanentNumber": "1",
                            "code": "VER",
                            "url": "http://en.wikipedia.org/wiki/Max_Verstappen",
                            "givenName": "Max",
                            "familyName": "Verstappen",
                            "dateOfBirth": "1997-09-30",
                            "nationality": "Dutch"
                          },
                          "Constructors": [
                            {
                              "constructorId": "red_bull",
                              "url": "http://en.wikipedia.org/wiki/Red_Bull_Racing",
                              "name": "Red Bull",
                              "nationality": "Austrian"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }
              }
            }
        """.trimIndent()

        val response = json.decodeFromString<ErgastResponse>(sampleJson)
        assertNotNull(response.mrData.standingsTable)
        val standingsList = response.mrData.standingsTable?.standingsLists?.firstOrNull()
        assertNotNull(standingsList)
        assertEquals("2023", standingsList.season)

        val driverStanding = standingsList.driverStandings?.firstOrNull()
        assertNotNull(driverStanding)
        assertEquals("1", driverStanding.position)
        assertEquals("575", driverStanding.points)
        assertEquals("max_verstappen", driverStanding.driver.driverId)
        assertEquals("Max", driverStanding.driver.givenName)
        assertEquals("Verstappen", driverStanding.driver.familyName)
        assertEquals("red_bull", driverStanding.constructors.first().constructorId)
    }

    @Test
    fun testRaceResultsResponseDeserialization() {
        val sampleJson = """
            {
              "MRData": {
                "series": "f1",
                "limit": "30",
                "offset": "0",
                "total": "1",
                "RaceTable": {
                  "season": "2023",
                  "round": "1",
                  "Races": [
                    {
                      "season": "2023",
                      "round": "1",
                      "url": "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
                      "raceName": "Bahrain Grand Prix",
                      "Circuit": {
                        "circuitId": "bahrain",
                        "url": "http://en.wikipedia.org/wiki/Bahrain_International_Circuit",
                        "circuitName": "Bahrain International Circuit",
                        "Location": {
                          "lat": "26.0325",
                          "long": "50.5106",
                          "locality": "Sakhir",
                          "country": "Bahrain"
                        }
                      },
                      "date": "2023-03-05",
                      "time": "15:00:00Z",
                      "Results": [
                        {
                          "number": "1",
                          "position": "1",
                          "positionText": "1",
                          "points": "25",
                          "Driver": {
                            "driverId": "max_verstappen",
                            "givenName": "Max",
                            "familyName": "Verstappen"
                          },
                          "Constructor": {
                            "constructorId": "red_bull",
                            "name": "Red Bull"
                          },
                          "grid": "1",
                          "laps": "57",
                          "status": "Finished"
                        }
                      ]
                    }
                  ]
                }
              }
            }
        """.trimIndent()

        val response = json.decodeFromString<ErgastResponse>(sampleJson)
        assertNotNull(response.mrData.raceTable)
        val race = response.mrData.raceTable?.races?.firstOrNull()
        assertNotNull(race)
        assertEquals("Bahrain Grand Prix", race.raceName)
        assertEquals("bahrain", race.circuit?.circuitId)
        assertEquals("Bahrain", race.circuit?.location?.country)

        val result = race.results?.firstOrNull()
        assertNotNull(result)
        assertEquals("1", result.position)
        assertEquals("Finished", result.status)
        assertEquals("Max", result.driver.givenName)
    }
}
