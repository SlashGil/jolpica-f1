package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.RaceModel
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

class GetPitStopsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String,
        round: String,
        driverId: String? = null,
        stopNumber: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<RaceModel>> = runCatching {
        repository.getPitStops(year, round, driverId, stopNumber, limit, offset)
    }
}
