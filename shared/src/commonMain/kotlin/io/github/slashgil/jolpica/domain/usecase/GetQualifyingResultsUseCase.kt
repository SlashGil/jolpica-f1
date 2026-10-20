package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.RaceModel
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

class GetQualifyingResultsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<RaceModel>> = runCatching {
        repository.getQualifyingResults(year, round, driverId, constructorId, limit, offset)
    }.toResult()

    fun asFlow(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<RaceModel>>> = suspend {
        repository.getQualifyingResults(year, round, driverId, constructorId, limit, offset)
    }.asResultFlow()
}
