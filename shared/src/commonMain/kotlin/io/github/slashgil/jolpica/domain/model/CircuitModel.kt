package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.Circuit

data class CircuitModel(
    val circuitId: String,
    val url: String? = null,
    val circuitName: String,
    val location: LocationModel? = null
) {
    companion object {
        fun from(circuit: Circuit): CircuitModel = CircuitModel(
            circuitId = circuit.circuitId,
            url = circuit.url,
            circuitName = circuit.circuitName,
            location = circuit.location?.let { LocationModel.from(it) }
        )
    }
}
