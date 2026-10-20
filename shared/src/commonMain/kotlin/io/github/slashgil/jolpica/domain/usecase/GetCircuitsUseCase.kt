package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.CircuitModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

class GetCircuitsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String? = null,
        round: String? = null,
        driverId: String? = null,
        constructorId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<CircuitModel>> = runCatching {
        repository.getCircuits(year, round, driverId, constructorId, limit, offset)
    }
}
