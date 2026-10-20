package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.Location

data class LocationModel(
    val lat: Double? = null,
    val long: Double? = null,
    val locality: String? = null,
    val country: String? = null
) {
    companion object {
        fun from(location: Location): LocationModel = LocationModel(
            lat = location.lat?.toDoubleOrNull(),
            long = location.long?.toDoubleOrNull(),
            locality = location.locality,
            country = location.country
        )
    }
}
