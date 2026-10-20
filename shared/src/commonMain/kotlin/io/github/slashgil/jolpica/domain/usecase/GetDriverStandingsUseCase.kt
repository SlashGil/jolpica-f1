package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.DriverStandingModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

class GetDriverStandingsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<DriverStandingModel>> = runCatching {
        repository.getDriverStandings(year, round, driverId, position, limit, offset)
    }.toResult()

    fun asFlow(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<DriverStandingModel>>> = suspend {
        repository.getDriverStandings(year, round, driverId, position, limit, offset)
    }.asResultFlow()
}
