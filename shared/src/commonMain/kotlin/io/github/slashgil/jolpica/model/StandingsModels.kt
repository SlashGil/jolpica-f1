package io.github.slashgil.jolpica.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsTable(
    val season: String? = null,
    val round: String? = null,
    val driverId: String? = null,
    val constructorId: String? = null,
    @SerialName("StandingsLists")
    val standingsLists: List<StandingsList> = emptyList()
)

@Serializable
data class StandingsList(
    val season: String,
    val round: String,
    @SerialName("DriverStandings")
    val driverStandings: List<DriverStanding>? = null,
    @SerialName("ConstructorStandings")
    val constructorStandings: List<ConstructorStanding>? = null
)

@Serializable
data class DriverStanding(
    val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    @SerialName("Driver")
    val driver: Driver,
    @SerialName("Constructors")
    val constructors: List<Constructor> = emptyList()
)

@Serializable
data class ConstructorStanding(
    val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    @SerialName("Constructor")
    val constructor: Constructor
)
