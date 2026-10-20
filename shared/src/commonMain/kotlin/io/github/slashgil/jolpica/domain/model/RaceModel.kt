package io.github.slashgil.jolpica.domain.model

data class RaceModel(
    val season: Int,
    val round: Int,
    val url: String? = null,
    val raceName: String,
    val circuit: CircuitModel? = null,
    val date: String? = null,
    val time: String? = null,
    val firstPractice: ScheduleTimeModel? = null,
    val secondPractice: ScheduleTimeModel? = null,
    val thirdPractice: ScheduleTimeModel? = null,
    val qualifying: ScheduleTimeModel? = null,
    val sprint: ScheduleTimeModel? = null,
    val results: List<ResultModel>? = null,
    val qualifyingResults: List<QualifyingResultModel>? = null,
    val sprintResults: List<SprintResultModel>? = null,
    val pitStops: List<PitStopModel>? = null,
    val laps: List<LapModel>? = null
)

data class ScheduleTimeModel(
    val date: String? = null,
    val time: String? = null
)
