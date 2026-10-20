package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.PitStop

data class PitStopModel(
    val driverId: String,
    val lap: Int,
    val stop: Int,
    val time: String,
    val duration: String
) {
    companion object {
        fun from(pitStop: PitStop): PitStopModel = PitStopModel(
            driverId = pitStop.driverId,
            lap = pitStop.lap.toIntOrNull() ?: 0,
            stop = pitStop.stop.toIntOrNull() ?: 0,
            time = pitStop.time,
            duration = pitStop.duration
        )
    }
}
