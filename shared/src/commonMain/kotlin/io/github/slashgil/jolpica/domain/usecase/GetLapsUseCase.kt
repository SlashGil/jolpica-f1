package io.github.slashgil.jolpica.domain.usecase

import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.RaceModel
import io.github.slashgil.jolpica.domain.model.Result
import io.github.slashgil.jolpica.domain.model.asResultFlow
import io.github.slashgil.jolpica.domain.model.toResult
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import kotlinx.coroutines.flow.Flow

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
    }.toResult()

    fun asFlow(
        year: String,
        round: String,
        lapNumber: String? = null,
        driverId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Result<Page<RaceModel>>> = suspend {
        repository.getLaps(year, round, lapNumber, driverId, limit, offset)
    }.asResultFlow()
}
