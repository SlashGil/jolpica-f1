package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.RaceModel
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

class GetPitStopsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String,
        round: String,
        driverId: String? = null,
        stopNumber: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<RaceModel>> = runCatching {
        repository.getPitStops(year, round, driverId, stopNumber, limit, offset)
    }.toResult()

    fun asFlow(
        year: String,
        round: String,
        driverId: String? = null,
        stopNumber: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<RaceModel>>> = suspend {
        repository.getPitStops(year, round, driverId, stopNumber, limit, offset)
    }.asResultFlow()
}
