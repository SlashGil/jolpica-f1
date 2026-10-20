package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.SeasonModel
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

class GetSeasonsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        driverId: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<SeasonModel>> = runCatching {
        repository.getSeasons(driverId, constructorId, circuitId, limit, offset)
    }.toResult()

    fun asFlow(
        driverId: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<SeasonModel>>> = suspend {
        repository.getSeasons(driverId, constructorId, circuitId, limit, offset)
    }.asResultFlow()
}
