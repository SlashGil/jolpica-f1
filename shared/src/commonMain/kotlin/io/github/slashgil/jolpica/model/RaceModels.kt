package io.github.slashgil.jolpica.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceTable(
    val season: String? = null,
    val round: String? = null,
    val circuitId: String? = null,
    val driverId: String? = null,
    val constructorId: String? = null,
    @SerialName("Races")
    val races: List<Race> = emptyList()
)

@Serializable
data class Race(
    val season: String,
    val round: String,
    val url: String? = null,
    val raceName: String,
    @SerialName("Circuit")
    val circuit: Circuit? = null,
    val date: String? = null,
    val time: String? = null,
    @SerialName("FirstPractice")
    val firstPractice: ScheduleTime? = null,
    @SerialName("SecondPractice")
    val secondPractice: ScheduleTime? = null,
    @SerialName("ThirdPractice")
    val thirdPractice: ScheduleTime? = null,
    @SerialName("Qualifying")
    val qualifying: ScheduleTime? = null,
    @SerialName("Sprint")
    val sprint: ScheduleTime? = null,
    @SerialName("Results")
    val results: List<Result>? = null,
    @SerialName("QualifyingResults")
    val qualifyingResults: List<QualifyingResult>? = null,
    @SerialName("SprintResults")
    val sprintResults: List<SprintResult>? = null,
    @SerialName("PitStops")
    val pitStops: List<PitStop>? = null,
    @SerialName("Laps")
    val laps: List<Lap>? = null
)

@Serializable
data class Result(
    val number: String? = null,
    val position: String,
    val positionText: String,
    val points: String? = null,
    @SerialName("Driver")
    val driver: Driver,
    @SerialName("Constructor")
    val constructor: Constructor,
    val grid: String? = null,
    val laps: String? = null,
    val status: String? = null,
    @SerialName("Time")
    val time: TimeInfo? = null,
    @SerialName("FastestLap")
    val fastestLap: FastestLap? = null
)

@Serializable
data class SprintResult(
    val number: String? = null,
    val position: String,
    val positionText: String,
    val points: String? = null,
    @SerialName("Driver")
    val driver: Driver,
    @SerialName("Constructor")
    val constructor: Constructor,
    val grid: String? = null,
    val laps: String? = null,
    val status: String? = null,
    @SerialName("Time")
    val time: TimeInfo? = null,
    @SerialName("FastestLap")
    val fastestLap: FastestLap? = null
)

@Serializable
data class QualifyingResult(
    val number: String? = null,
    val position: String,
    @SerialName("Driver")
    val driver: Driver,
    @SerialName("Constructor")
    val constructor: Constructor,
    @SerialName("Q1")
    val q1: String? = null,
    @SerialName("Q2")
    val q2: String? = null,
    @SerialName("Q3")
    val q3: String? = null
)

@Serializable
data class FastestLap(
    val rank: String? = null,
    val lap: String? = null,
    @SerialName("Time")
    val time: TimeInfo? = null,
    @SerialName("AverageSpeed")
    val averageSpeed: AverageSpeed? = null
)

@Serializable
data class PitStop(
    val driverId: String,
    val lap: String,
    val stop: String,
    val time: String,
    val duration: String
)

@Serializable
data class Lap(
    val number: String,
    @SerialName("Timings")
    val timings: List<Timing> = emptyList()
)

@Serializable
data class Timing(
    val driverId: String,
    val position: String,
    val time: String
)
