package io.github.slashgil.jolpica.domain.model

data class SprintResultModel(
    val number: Int? = null,
    val position: Int,
    val positionText: String,
    val points: Double,
    val driver: DriverModel,
    val constructor: ConstructorModel,
    val grid: Int? = null,
    val laps: Int? = null,
    val status: String? = null,
    val time: TimeInfoModel? = null,
    val fastestLap: FastestLapModel? = null
)
