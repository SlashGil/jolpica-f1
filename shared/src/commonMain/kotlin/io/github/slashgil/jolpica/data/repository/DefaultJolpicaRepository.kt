package io.github.slashgil.jolpica.data.repository

import io.github.slashgil.jolpica.client.JolpicaClient
import io.github.slashgil.jolpica.data.mapper.toCircuitsPage
import io.github.slashgil.jolpica.data.mapper.toConstructorStandingsPage
import io.github.slashgil.jolpica.data.mapper.toConstructorsPage
import io.github.slashgil.jolpica.data.mapper.toDriverStandingsPage
import io.github.slashgil.jolpica.data.mapper.toDriversPage
import io.github.slashgil.jolpica.data.mapper.toRacesPage
import io.github.slashgil.jolpica.data.mapper.toSeasonsPage
import io.github.slashgil.jolpica.data.mapper.toStatusesPage
import io.github.slashgil.jolpica.domain.model.CircuitModel
import io.github.slashgil.jolpica.domain.model.ConstructorModel
import io.github.slashgil.jolpica.domain.model.ConstructorStandingModel
import io.github.slashgil.jolpica.domain.model.DriverModel
import io.github.slashgil.jolpica.domain.model.DriverStandingModel
import io.github.slashgil.jolpica.domain.model.JolpicaException
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.RaceModel
import io.github.slashgil.jolpica.domain.model.SeasonModel
import io.github.slashgil.jolpica.domain.model.StatusModel
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

class DefaultJolpicaRepository(
    private val client: JolpicaClient
) : JolpicaRepository {

    private inline fun <T> safeCall(block: () -> T): T {
        return try {
            block()
        } catch (e: JolpicaException) {
            throw e
        } catch (e: Throwable) {
            throw JolpicaException.NetworkException(e)
        }
    }

    override suspend fun getSeasons(
        driverId: String?,
        constructorId: String?,
        circuitId: String?,
        limit: Int?,
        offset: Int?
    ): Page<SeasonModel> = safeCall {
        client.getSeasons(driverId, constructorId, circuitId, limit, offset).toSeasonsPage()
    }

    override suspend fun getCircuits(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): Page<CircuitModel> = safeCall {
        client.getCircuits(year, round, driverId, constructorId, limit, offset).toCircuitsPage()
    }

    override suspend fun getConstructors(
        year: String?,
        round: String?,
        driverId: String?,
        circuitId: String?,
        limit: Int?,
        offset: Int?
    ): Page<ConstructorModel> = safeCall {
        client.getConstructors(year, round, driverId, circuitId, limit, offset).toConstructorsPage()
    }

    override suspend fun getDrivers(
        year: String?,
        round: String?,
        constructorId: String?,
        circuitId: String?,
        limit: Int?,
        offset: Int?
    ): Page<DriverModel> = safeCall {
        client.getDrivers(year, round, constructorId, circuitId, limit, offset).toDriversPage()
    }

    override suspend fun getDriverStandings(
        year: String?,
        round: String?,
        driverId: String?,
        position: String?,
        limit: Int?,
        offset: Int?
    ): Page<DriverStandingModel> = safeCall {
        client.getDriverStandings(year, round, driverId, position, limit, offset).toDriverStandingsPage()
    }

    override suspend fun getConstructorStandings(
        year: String?,
        round: String?,
        constructorId: String?,
        position: String?,
        limit: Int?,
        offset: Int?
    ): Page<ConstructorStandingModel> = safeCall {
        client.getConstructorStandings(year, round, constructorId, position, limit, offset).toConstructorStandingsPage()
    }

    override suspend fun getSchedule(
        year: String?,
        round: String?,
        circuitId: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): Page<RaceModel> = safeCall {
        client.getSchedule(year, round, circuitId, driverId, constructorId, limit, offset).toRacesPage()
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
    ): Page<RaceModel> = safeCall {
        client.getRaceResults(year, round, driverId, constructorId, circuitId, grid, fastest, statusId, limit, offset).toRacesPage()
    }

    override suspend fun getQualifyingResults(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): Page<RaceModel> = safeCall {
        client.getQualifyingResults(year, round, driverId, constructorId, limit, offset).toRacesPage()
    }

    override suspend fun getSprintResults(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): Page<RaceModel> = safeCall {
        client.getSprintResults(year, round, driverId, constructorId, limit, offset).toRacesPage()
    }

    override suspend fun getPitStops(
        year: String,
        round: String,
        driverId: String?,
        stopNumber: String?,
        limit: Int?,
        offset: Int?
    ): Page<RaceModel> = safeCall {
        client.getPitStops(year, round, driverId, stopNumber, limit, offset).toRacesPage()
    }

    override suspend fun getLaps(
        year: String,
        round: String,
        lapNumber: String?,
        driverId: String?,
        limit: Int?,
        offset: Int?
    ): Page<RaceModel> = safeCall {
        client.getLaps(year, round, lapNumber, driverId, limit, offset).toRacesPage()
    }

    override suspend fun getStatuses(
        year: String?,
        round: String?,
        driverId: String?,
        constructorId: String?,
        limit: Int?,
        offset: Int?
    ): Page<StatusModel> = safeCall {
        client.getStatuses(year, round, driverId, constructorId, limit, offset).toStatusesPage()
    }
}
