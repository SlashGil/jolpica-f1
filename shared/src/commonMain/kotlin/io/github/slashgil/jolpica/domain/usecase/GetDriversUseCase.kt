package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.DriverModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository

class GetDriversUseCase(
    private val repository: JolpicaRepository
) {
    suspend operator fun invoke(
        year: String? = null,
        round: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Result<Page<DriverModel>> = runCatching {
        repository.getDrivers(year, round, constructorId, circuitId, limit, offset)
    }
}
