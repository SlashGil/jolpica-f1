package io.github.slashgil.jolpica.domain.model

data class PitStopModel(
    val driverId: String,
    val lap: Int,
    val stop: Int,
    val time: String,
    val duration: String
)
