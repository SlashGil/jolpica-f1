package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.SeasonModel
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

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
    }
}
