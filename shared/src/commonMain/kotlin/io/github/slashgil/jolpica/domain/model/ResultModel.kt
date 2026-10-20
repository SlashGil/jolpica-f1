package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.AverageSpeed
import io.github.slashgil.jolpica.model.FastestLap
import io.github.slashgil.jolpica.model.Result
import io.github.slashgil.jolpica.model.TimeInfo

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
) {
    companion object {
        fun from(result: Result): ResultModel = ResultModel(
            number = result.number?.toIntOrNull(),
            position = result.position.toIntOrNull() ?: 0,
            positionText = result.positionText,
            points = result.points?.toDoubleOrNull() ?: 0.0,
            driver = DriverModel.from(result.driver),
            constructor = ConstructorModel.from(result.constructor),
            grid = result.grid?.toIntOrNull(),
            laps = result.laps?.toIntOrNull(),
            status = result.status,
            time = result.time?.let { TimeInfoModel.from(it) },
            fastestLap = result.fastestLap?.let { FastestLapModel.from(it) }
        )
    }
}

data class TimeInfoModel(
    val millis: Long? = null,
    val time: String? = null
) {
    companion object {
        fun from(timeInfo: TimeInfo): TimeInfoModel = TimeInfoModel(
            millis = timeInfo.millis?.toLongOrNull(),
            time = timeInfo.time
        )
    }
}

data class FastestLapModel(
    val rank: Int? = null,
    val lap: Int? = null,
    val time: TimeInfoModel? = null,
    val averageSpeed: AverageSpeedModel? = null
) {
    companion object {
        fun from(fastestLap: FastestLap): FastestLapModel = FastestLapModel(
            rank = fastestLap.rank?.toIntOrNull(),
            lap = fastestLap.lap?.toIntOrNull(),
            time = fastestLap.time?.let { TimeInfoModel.from(it) },
            averageSpeed = fastestLap.averageSpeed?.let { AverageSpeedModel.from(it) }
        )
    }
}

data class AverageSpeedModel(
    val units: String? = null,
    val speed: Double? = null
) {
    companion object {
        fun from(averageSpeed: AverageSpeed): AverageSpeedModel = AverageSpeedModel(
            units = averageSpeed.units,
            speed = averageSpeed.speed?.toDoubleOrNull()
        )
    }
}
