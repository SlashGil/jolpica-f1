package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.ConstructorStandingModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

class GetConstructorStandingsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String? = "current",
        round: String? = null,
        constructorId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<ConstructorStandingModel>> = runCatching {
        repository.getConstructorStandings(year, round, constructorId, position, limit, offset)
    }.toResult()

    fun asFlow(
        year: String? = "current",
        round: String? = null,
        constructorId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<ConstructorStandingModel>>> = suspend {
        repository.getConstructorStandings(year, round, constructorId, position, limit, offset)
    }.asResultFlow()
}
