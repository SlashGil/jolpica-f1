package io.github.slashgil.jolpica.domain.model

data class QualifyingResultModel(
    val number: Int? = null,
    val position: Int,
    val driver: DriverModel,
    val constructor: ConstructorModel,
    val q1: String? = null,
    val q2: String? = null,
    val q3: String? = null
)
