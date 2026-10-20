package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.ConstructorModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

class GetConstructorsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<ConstructorModel>> = runCatching {
        repository.getConstructors(year, round, driverId, circuitId, limit, offset)
    }.toResult()

    fun asFlow(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<ConstructorModel>>> = suspend {
        repository.getConstructors(year, round, driverId, circuitId, limit, offset)
    }.asResultFlow()
}
