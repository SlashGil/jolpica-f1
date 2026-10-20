package io.github.slashgil.jolpica.domain.model

data class LapModel(
    val number: Int,
    val timings: List<TimingModel> = emptyList()
)

data class TimingModel(
    val driverId: String,
    val position: Int,
    val time: String
)
