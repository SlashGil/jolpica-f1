package io.github.slashgil.jolpica.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverTable(
    val season: String? = null,
    val driverId: String? = null,
    @SerialName("Drivers")
    val drivers: List<Driver> = emptyList()
)

@Serializable
data class ConstructorTable(
    val season: String? = null,
    val constructorId: String? = null,
    @SerialName("Constructors")
    val constructors: List<Constructor> = emptyList()
)

@Serializable
data class CircuitTable(
    val season: String? = null,
    val circuitId: String? = null,
    @SerialName("Circuits")
    val circuits: List<Circuit> = emptyList()
)

@Serializable
data class StatusTable(
    val season: String? = null,
    @SerialName("Status")
    val statusList: List<Status> = emptyList()
)

@Serializable
data class SeasonTable(
    @SerialName("Seasons")
    val seasons: List<Season> = emptyList()
)
