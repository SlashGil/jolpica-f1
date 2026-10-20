package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.Lap
import io.github.slashgil.jolpica.model.Timing

data class LapModel(
    val number: Int,
    val timings: List<TimingModel> = emptyList()
) {
    companion object {
        fun from(lap: Lap): LapModel = LapModel(
            number = lap.number.toIntOrNull() ?: 0,
            timings = lap.timings.map { TimingModel.from(it) }
        )
    }
}

data class TimingModel(
    val driverId: String,
    val position: Int,
    val time: String
) {
    companion object {
        fun from(timing: Timing): TimingModel = TimingModel(
            driverId = timing.driverId,
            position = timing.position.toIntOrNull() ?: 0,
            time = timing.time
        )
    }
}
