package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.StatusModel
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

class GetStatusesUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<StatusModel>> = runCatching {
        repository.getStatuses(year, round, driverId, constructorId, limit, offset)
    }.toResult()

    fun asFlow(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<StatusModel>>> = suspend {
        repository.getStatuses(year, round, driverId, constructorId, limit, offset)
    }.asResultFlow()
}
