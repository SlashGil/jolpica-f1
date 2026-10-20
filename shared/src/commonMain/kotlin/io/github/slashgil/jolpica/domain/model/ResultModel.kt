package io.github.slashgil.jolpica.domain.model

data class ResultModel(
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

data class TimeInfoModel(
    val millis: Long? = null,
    val time: String? = null
)

data class FastestLapModel(
    val rank: Int? = null,
    val lap: Int? = null,
    val time: TimeInfoModel? = null,
    val averageSpeed: AverageSpeedModel? = null
)

data class AverageSpeedModel(
    val units: String? = null,
    val speed: Double? = null
)
