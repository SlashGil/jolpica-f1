package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.RaceModel
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

class GetLapsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String,
        round: String,
        lapNumber: String? = null,
        driverId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<RaceModel>> = runCatching {
        repository.getLaps(year, round, lapNumber, driverId, limit, offset)
    }
}
