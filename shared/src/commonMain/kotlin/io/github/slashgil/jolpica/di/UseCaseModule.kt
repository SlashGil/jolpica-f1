package io.github.slashgil.jolpica.di

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
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetSeasonsUseCase(repository = get()) }
    factory { GetCircuitsUseCase(repository = get()) }
    factory { GetConstructorsUseCase(repository = get()) }
    factory { GetDriversUseCase(repository = get()) }
    factory { GetDriverStandingsUseCase(repository = get()) }
    factory { GetConstructorStandingsUseCase(repository = get()) }
    factory { GetScheduleUseCase(repository = get()) }
    factory { GetRaceResultsUseCase(repository = get()) }
    factory { GetQualifyingResultsUseCase(repository = get()) }
    factory { GetSprintResultsUseCase(repository = get()) }
    factory { GetPitStopsUseCase(repository = get()) }
    factory { GetLapsUseCase(repository = get()) }
    factory { GetStatusesUseCase(repository = get()) }
}
