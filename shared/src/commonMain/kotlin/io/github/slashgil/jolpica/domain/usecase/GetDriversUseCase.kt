package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.DriverModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

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
    }.toResult()

    fun asFlow(
        year: String? = null,
        round: String? = null,
        constructorId: String? = null,
        circuitId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<DriverModel>>> = suspend {
        repository.getDrivers(year, round, constructorId, circuitId, limit, offset)
    }.asResultFlow()
}
