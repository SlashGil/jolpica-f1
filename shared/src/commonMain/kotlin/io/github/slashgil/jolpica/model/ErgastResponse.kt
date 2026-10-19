package io.github.slashgil.jolpica.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErgastResponse(
    @SerialName("MRData")
    val mrData: MRData
)

@Serializable
data class MRData(
    val xmlns: String? = null,
    val series: String? = null,
    val url: String? = null,
    val limit: String? = null,
    val offset: String? = null,
    val total: String? = null,
    @SerialName("RaceTable")
    val raceTable: RaceTable? = null,
    @SerialName("StandingsTable")
    val standingsTable: StandingsTable? = null,
    @SerialName("DriverTable")
    val driverTable: DriverTable? = null,
    @SerialName("ConstructorTable")
    val constructorTable: ConstructorTable? = null,
    @SerialName("CircuitTable")
    val circuitTable: CircuitTable? = null,
    @SerialName("StatusTable")
    val statusTable: StatusTable? = null,
    @SerialName("SeasonTable")
    val seasonTable: SeasonTable? = null
)
