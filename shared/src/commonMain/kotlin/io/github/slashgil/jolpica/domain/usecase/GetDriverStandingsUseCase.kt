package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.DriverStandingModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

class GetDriverStandingsUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String? = "current",
        round: String? = null,
        driverId: String? = null,
        position: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<DriverStandingModel>> = runCatching {
        repository.getDriverStandings(year, round, driverId, position, limit, offset)
    }
}
