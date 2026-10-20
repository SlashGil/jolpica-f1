package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.ConstructorStanding
import io.github.slashgil.jolpica.model.DriverStanding

data class DriverStandingModel(
    val position: Int,
    val positionText: String,
    val points: Double,
    val wins: Int,
    val driver: DriverModel,
    val constructors: List<ConstructorModel> = emptyList()
) {
    companion object {
        fun from(driverStanding: DriverStanding): DriverStandingModel = DriverStandingModel(
            position = driverStanding.position.toIntOrNull() ?: 0,
            positionText = driverStanding.positionText,
            points = driverStanding.points.toDoubleOrNull() ?: 0.0,
            wins = driverStanding.wins.toIntOrNull() ?: 0,
            driver = DriverModel.from(driverStanding.driver),
            constructors = driverStanding.constructors.map { ConstructorModel.from(it) }
        )
    }
}

data class ConstructorStandingModel(
    val position: Int,
    val positionText: String,
    val points: Double,
    val wins: Int,
    val constructor: ConstructorModel
) {
    companion object {
        fun from(constructorStanding: ConstructorStanding): ConstructorStandingModel = ConstructorStandingModel(
            position = constructorStanding.position.toIntOrNull() ?: 0,
            positionText = constructorStanding.positionText,
            points = constructorStanding.points.toDoubleOrNull() ?: 0.0,
            wins = constructorStanding.wins.toIntOrNull() ?: 0,
            constructor = ConstructorModel.from(constructorStanding.constructor)
        )
    }
}
