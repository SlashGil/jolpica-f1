package io.github.slashgil.jolpica.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    val driverId: String,
    val permanentNumber: String? = null,
    val code: String? = null,
    val url: String? = null,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String? = null,
    val nationality: String? = null
)

@Serializable
data class Constructor(
    val constructorId: String,
    val url: String? = null,
    val name: String,
    val nationality: String? = null
)

@Serializable
data class Location(
    val lat: String? = null,
    val long: String? = null,
    val locality: String? = null,
    val country: String? = null
)

@Serializable
data class Circuit(
    val circuitId: String,
    val url: String? = null,
    val circuitName: String,
    @SerialName("Location")
    val location: Location? = null
)

@Serializable
data class Season(
    val season: String,
    val url: String? = null
)

@Serializable
data class Status(
    val statusId: String,
    val count: String,
    val status: String
)

@Serializable
data class ScheduleTime(
    val date: String? = null,
    val time: String? = null
)

@Serializable
data class TimeInfo(
    val millis: String? = null,
    val time: String? = null
)

@Serializable
data class AverageSpeed(
    val units: String? = null,
    val speed: String? = null
)
