package io.github.slashgil.jolpica.domain.model

data class DriverStandingModel(
    val position: Int,
    val positionText: String,
    val points: Double,
    val wins: Int,
    val driver: DriverModel,
    val constructors: List<ConstructorModel> = emptyList()
)

data class ConstructorStandingModel(
    val position: Int,
    val positionText: String,
    val points: Double,
    val wins: Int,
    val constructor: ConstructorModel
)
