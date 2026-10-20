package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.ConstructorStandingModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

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
    }
}
