package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.ConstructorModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

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
    }
}
