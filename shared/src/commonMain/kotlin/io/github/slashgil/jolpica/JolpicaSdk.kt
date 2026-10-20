package io.github.slashgil.jolpica

import io.github.slashgil.jolpica.client.JolpicaClient
import io.github.slashgil.jolpica.data.repository.DefaultJolpicaRepository
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import io.github.slashgil.jolpica.domain.usecase.GetCircuitsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetConstructorStandingsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetConstructorsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetDriverStandingsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetDriversUseCase
import io.github.slashgil.jolpica.domain.usecase.GetLapsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetPitStopsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetQualifyingResultsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetRaceResultsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetScheduleUseCase
import io.github.slashgil.jolpica.domain.usecase.GetSeasonsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetSprintResultsUseCase
import io.github.slashgil.jolpica.domain.usecase.GetStatusesUseCase

class JolpicaSdk(
    val client: JolpicaClient = JolpicaClient(),
    val repository: JolpicaRepository = DefaultJolpicaRepository(client)
) {
    val getSeasons = GetSeasonsUseCase(repository)
    val getCircuits = GetCircuitsUseCase(repository)
    val getConstructors = GetConstructorsUseCase(repository)
    val getDrivers = GetDriversUseCase(repository)
    val getDriverStandings = GetDriverStandingsUseCase(repository)
    val getConstructorStandings = GetConstructorStandingsUseCase(repository)
    val getSchedule = GetScheduleUseCase(repository)
    val getRaceResults = GetRaceResultsUseCase(repository)
    val getQualifyingResults = GetQualifyingResultsUseCase(repository)
    val getSprintResults = GetSprintResultsUseCase(repository)
    val getPitStops = GetPitStopsUseCase(repository)
    val getLaps = GetLapsUseCase(repository)
    val getStatuses = GetStatusesUseCase(repository)

    fun close() {
        client.close()
    }
}
