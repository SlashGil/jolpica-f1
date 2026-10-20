package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.Race
import io.github.slashgil.jolpica.model.ScheduleTime

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
) {
    companion object {
        fun from(race: Race): RaceModel = RaceModel(
            season = race.season.toIntOrNull() ?: 0,
            round = race.round.toIntOrNull() ?: 0,
            url = race.url,
            raceName = race.raceName,
            circuit = race.circuit?.let { CircuitModel.from(it) },
            date = race.date,
            time = race.time,
            firstPractice = race.firstPractice?.let { ScheduleTimeModel.from(it) },
            secondPractice = race.secondPractice?.let { ScheduleTimeModel.from(it) },
            thirdPractice = race.thirdPractice?.let { ScheduleTimeModel.from(it) },
            qualifying = race.qualifying?.let { ScheduleTimeModel.from(it) },
            sprint = race.sprint?.let { ScheduleTimeModel.from(it) },
            results = race.results?.map { ResultModel.from(it) },
            qualifyingResults = race.qualifyingResults?.map { QualifyingResultModel.from(it) },
            sprintResults = race.sprintResults?.map { SprintResultModel.from(it) },
            pitStops = race.pitStops?.map { PitStopModel.from(it) },
            laps = race.laps?.map { LapModel.from(it) }
        )
    }
}

data class ScheduleTimeModel(
    val date: String? = null,
    val time: String? = null
) {
    companion object {
        fun from(scheduleTime: ScheduleTime): ScheduleTimeModel = ScheduleTimeModel(
            date = scheduleTime.date,
            time = scheduleTime.time
        )
    }
}
