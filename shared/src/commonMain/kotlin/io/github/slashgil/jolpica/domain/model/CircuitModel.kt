package io.github.slashgil.jolpica.domain.model

data class CircuitModel(
    val circuitId: String,
    val url: String? = null,
    val circuitName: String,
    val location: LocationModel? = null
)
