package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.QualifyingResult

data class QualifyingResultModel(
    val number: Int? = null,
    val position: Int,
    val driver: DriverModel,
    val constructor: ConstructorModel,
    val q1: String? = null,
    val q2: String? = null,
    val q3: String? = null
) {
    companion object {
        fun from(qualifyingResult: QualifyingResult): QualifyingResultModel = QualifyingResultModel(
            number = qualifyingResult.number?.toIntOrNull(),
            position = qualifyingResult.position.toIntOrNull() ?: 0,
            driver = DriverModel.from(qualifyingResult.driver),
            constructor = ConstructorModel.from(qualifyingResult.constructor),
            q1 = qualifyingResult.q1,
            q2 = qualifyingResult.q2,
            q3 = qualifyingResult.q3
        )
    }
}
