package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.StatusModel
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

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
    }
}
