package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.SprintResult

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
) {
    companion object {
        fun from(sprintResult: SprintResult): SprintResultModel = SprintResultModel(
            number = sprintResult.number?.toIntOrNull(),
            position = sprintResult.position.toIntOrNull() ?: 0,
            positionText = sprintResult.positionText,
            points = sprintResult.points?.toDoubleOrNull() ?: 0.0,
            driver = DriverModel.from(sprintResult.driver),
            constructor = ConstructorModel.from(sprintResult.constructor),
            grid = sprintResult.grid?.toIntOrNull(),
            laps = sprintResult.laps?.toIntOrNull(),
            status = sprintResult.status,
            time = sprintResult.time?.let { TimeInfoModel.from(it) },
            fastestLap = sprintResult.fastestLap?.let { FastestLapModel.from(it) }
        )
    }
}
