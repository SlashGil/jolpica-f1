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
        driverId: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getCircuits(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getConstructors(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getDrivers(
        year: String? = null,
        round: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getDriverStandings(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getConstructorStandings(
        year: String? = "current",
        round: String? = null,
        constructorId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getSchedule(
        year: String? = "current",
        round: String? = null,
        circuitId: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getRaceResults(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        grid: String? = null,
        fastest: String? = null,
        statusId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getQualifyingResults(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getSprintResults(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getPitStops(
        year: String,
        round: String,
        driverId: String? = null,
        stopNumber: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getLaps(
        year: String,
        round: String,
        lapNumber: String? = null,
        driverId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getStatuses(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): ErgastResponse

    suspend fun getCustomEndpoint(
        path: String,
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
        val cleanPath = path.trimStart('/')
        val fullUrl = "$formattedBaseUrl$cleanPath"
        return httpClient.get(fullUrl) {
            if (limit != null) parameter("limit", limit)
            if (offset != null) parameter("offset", offset)
        }.body()
    }

    override suspend fun getSeasons(
        driverId: String?,
        constructorId: String?,
        circuitId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(
            driverId = driverId,
            constructorId = constructorId,
            circuitId = circuitId,
            resource = "seasons.json"
        )
        return request(path, limit, offset)
    }

    override suspend fun getCircuits(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            constructorId = constructorId,
            resource = "circuits.json"
        )
        return request(path, limit, offset)
    }

    override suspend fun getConstructors(
        year: String?,
        round: String?,
        driverId: String?,
        circuitId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            circuitId = circuitId,
            resource = "constructors.json"
        )
        return request(path, limit, offset)
    }

    override suspend fun getDrivers(
        year: String?,
        round: String?,
        constructorId: String?,
        circuitId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(
            year = year,
            round = round,
            constructorId = constructorId,
            circuitId = circuitId,
            resource = "drivers.json"
        )
        return request(path, limit, offset)
    }

    override suspend fun getDriverStandings(
        year: String?,
        round: String?,
        driverId: String?,
        position: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val resource = if (position != null) "driverstandings/$position.json" else "driverstandings.json"
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            resource = resource
        )
        return request(path, limit, offset)
    }

    override suspend fun getConstructorStandings(
        year: String?,
        round: String?,
        constructorId: String?,
        position: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val resource = if (position != null) "constructorstandings/$position.json" else "constructorstandings.json"
        val path = buildPath(
            year = year,
            round = round,
            constructorId = constructorId,
            resource = resource
        )
        return request(path, limit, offset)
    }

    override suspend fun getSchedule(
        year: String?,
        round: String?,
        circuitId: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val resource = if (year != null && round == null && circuitId == null && driverId == null && constructorId == null) {
            "$year.json"
        } else {
            ""
        }
        val path = if (resource.isNotEmpty()) {
            resource
        } else {
            buildPath(
                year = year,
                round = round,
                circuitId = circuitId,
                driverId = driverId,
                constructorId = constructorId,
                resource = if (year != null && round != null) "" else ".json"
            ).let { if (it.endsWith(".json")) it else "$it.json" }
        }
        return request(path, limit, offset)
    }

    override suspend fun getRaceResults(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        circuitId: String?,
        grid: String?,
        fastest: String?,
        statusId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            constructorId = constructorId,
            circuitId = circuitId,
            grid = grid,
            fastest = fastest,
            statusId = statusId,
            resource = "results.json"
        )
        return request(path, limit, offset)
    }

    override suspend fun getQualifyingResults(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            constructorId = constructorId,
            resource = "qualifying.json"
        )
        return request(path, limit, offset)
    }

    override suspend fun getSprintResults(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            constructorId = constructorId,
            resource = "sprint.json"
        )
        return request(path, limit, offset)
    }

    override suspend fun getPitStops(
        year: String,
        round: String,
        driverId: String?,
        stopNumber: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val resource = if (stopNumber != null) "pitstops/$stopNumber.json" else "pitstops.json"
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            resource = resource
        )
        return request(path, limit, offset)
    }

    override suspend fun getLaps(
        year: String,
        round: String,
        lapNumber: String?,
        driverId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val resource = if (lapNumber != null) "laps/$lapNumber.json" else "laps.json"
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            resource = resource
        )
        return request(path, limit, offset)
    }

    override suspend fun getStatuses(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val path = buildPath(
            year = year,
            round = round,
            driverId = driverId,
            constructorId = constructorId,
            resource = "status.json"
        )
        return request(path, limit, offset)
    }

    override suspend fun getCustomEndpoint(
        path: String,
        limit: Int?,
        offset: Int?
    ): ErgastResponse {
        val cleanPath = if (path.endsWith(".json")) path else "$path.json"
        return request(cleanPath, limit, offset)
    }

    override fun close() {
        httpClient.close()
    }

    private fun buildPath(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        grid: String? = null,
        fastest: String? = null,
        statusId: String? = null,
        resource: String
    ): String {
        val segments = mutableListOf<String>()
        if (year != null) {
            segments.add(year)
            if (round != null) {
                segments.add(round)
            }
        }
        if (driverId != null) {
            segments.add("drivers")
            segments.add(driverId)
        }
        if (constructorId != null) {
            segments.add("constructors")
            segments.add(constructorId)
        }
        if (circuitId != null) {
            segments.add("circuits")
            segments.add(circuitId)
        }
        if (grid != null) {
            segments.add("grid")
            segments.add(grid)
        }
        if (fastest != null) {
            segments.add("fastest")
            segments.add(fastest)
        }
        if (statusId != null) {
            segments.add("status")
            segments.add(statusId)
        }
        if (resource.isNotEmpty()) {
            segments.add(resource)
        }
        return segments.joinToString("/")
    }
}
