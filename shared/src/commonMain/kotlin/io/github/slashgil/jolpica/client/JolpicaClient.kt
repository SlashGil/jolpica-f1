package io.github.slashgil.jolpica.client

import io.github.slashgil.jolpica.model.ErgastResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createDefaultHttpClient(): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

fun JolpicaClient(
    httpClient: HttpClient = createDefaultHttpClient(),
    baseUrl: String = "https://api.jolpi.ca/ergast/f1/"
): JolpicaClient = DefaultJolpicaClient(httpClient, baseUrl)

interface JolpicaClient {
    suspend fun getSeasons(
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getCircuits(
        year: String? = null,
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getConstructors(
        year: String? = null,
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getDrivers(
        year: String? = null,
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getDriverStandings(
        year: String = "current",
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getConstructorStandings(
        year: String = "current",
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getSchedule(
        year: String = "current",
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getRaceResults(
        year: String = "current",
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getQualifyingResults(
        year: String = "current",
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getSprintResults(
        year: String = "current",
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getPitStops(
        year: String,
        round: String,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getLaps(
        year: String,
        round: String,
        lapNumber: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getStatuses(
        year: String? = null,
        round: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    fun close()
}

class DefaultJolpicaClient(
    private val httpClient: HttpClient,
    baseUrl: String = "https://api.jolpi.ca/ergast/f1/"
) : JolpicaClient {

    private val formattedBaseUrl = if (baseUrl.endsWith("/")) baseUrl else "$baseUrl/"

    private suspend fun request(
        path: String,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse {
        val url = "$formattedBaseUrl${path.trimStart('/')}"
        return httpClient.get(url) {
            if (limit != null) parameter("limit", limit)
            if (offset != null) parameter("offset", offset)
        }.body()
    }

    override suspend fun getSeasons(limit: Int?, offset: Int?): ErgastResponse {
        return request("seasons.json", limit, offset)
    }

    override suspend fun getCircuits(
        year: String?,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(year, round, "circuits.json")
        return request(path, limit, offset)
    }

    override suspend fun getConstructors(
        year: String?,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(year, round, "constructors.json")
        return request(path, limit, offset)
    }

    override suspend fun getDrivers(
        year: String?,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(year, round, "drivers.json")
        return request(path, limit, offset)
    }

    override suspend fun getDriverStandings(
        year: String,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = if (round != null) "$year/$round/driverstandings.json" else "$year/driverstandings.json"
        return request(path, limit, offset)
    }

    override suspend fun getConstructorStandings(
        year: String,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = if (round != null) "$year/$round/constructorstandings.json" else "$year/constructorstandings.json"
        return request(path, limit, offset)
    }

    override suspend fun getSchedule(
        year: String,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = if (round != null) "$year/$round.json" else "$year.json"
        return request(path, limit, offset)
    }

    override suspend fun getRaceResults(
        year: String,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = if (round != null) "$year/$round/results.json" else "$year/results.json"
        return request(path, limit, offset)
    }

    override suspend fun getQualifyingResults(
        year: String,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = if (round != null) "$year/$round/qualifying.json" else "$year/qualifying.json"
        return request(path, limit, offset)
    }

    override suspend fun getSprintResults(
        year: String,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = if (round != null) "$year/$round/sprint.json" else "$year/sprint.json"
        return request(path, limit, offset)
    }

    override suspend fun getPitStops(
        year: String,
        round: String,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = "$year/$round/pitstops.json"
        return request(path, limit, offset)
    }

    override suspend fun getLaps(
        year: String,
        round: String,
        lapNumber: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = if (lapNumber != null) "$year/$round/laps/$lapNumber.json" else "$year/$round/laps.json"
        return request(path, limit, offset)
    }

    override suspend fun getStatuses(
        year: String?,
        round: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(year, round, "status.json")
        return request(path, limit, offset)
    }

    override fun close() {
        httpClient.close()
    }

    private fun buildPath(year: String?, round: String?, resource: String): String {
        return when {
            year != null && round != null -> "$year/$round/$resource"
            year != null -> "$year/$resource"
            else -> resource
        }
    }
}
