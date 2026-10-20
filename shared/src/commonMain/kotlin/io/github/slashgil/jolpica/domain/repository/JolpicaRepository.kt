package io.github.slashgil.jolpica.domain.repository

import io.github.slashgil.jolpica.domain.model.CircuitModel
import io.github.slashgil.jolpica.domain.model.ConstructorModel
import io.github.slashgil.jolpica.domain.model.ConstructorStandingModel
import io.github.slashgil.jolpica.domain.model.DriverModel
import io.github.slashgil.jolpica.domain.model.DriverStandingModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.RaceModel
import io.github.slashgil.jolpica.domain.model.SeasonModel
import io.github.slashgil.jolpica.domain.model.StatusModel

interface JolpicaRepository {
    suspend fun getSeasons(
        driverId: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<SeasonModel>

    suspend fun getCircuits(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<CircuitModel>

    suspend fun getConstructors(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<ConstructorModel>

    suspend fun getDrivers(
        year: String? = null,
        round: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<DriverModel>

    suspend fun getDriverStandings(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<DriverStandingModel>

    suspend fun getConstructorStandings(
        year: String? = "current",
        round: String? = null,
        constructorId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<ConstructorStandingModel>

    suspend fun getSchedule(
        year: String? = "current",
        round: String? = null,
        circuitId: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<RaceModel>

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
    ): Page<RaceModel>

    suspend fun getQualifyingResults(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<RaceModel>

    suspend fun getSprintResults(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<RaceModel>

    suspend fun getPitStops(
        year: String,
        round: String,
        driverId: String? = null,
        stopNumber: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<RaceModel>

    suspend fun getLaps(
        year: String,
        round: String,
        lapNumber: String? = null,
        driverId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<RaceModel>

    suspend fun getStatuses(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Page<StatusModel>
}
