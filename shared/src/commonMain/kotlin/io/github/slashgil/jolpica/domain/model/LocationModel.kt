package io.github.slashgil.jolpica.domain.model

data class LocationModel(
    val lat: Double? = null,
    val long: Double? = null,
    val locality: String? = null,
    val country: String? = null
)
