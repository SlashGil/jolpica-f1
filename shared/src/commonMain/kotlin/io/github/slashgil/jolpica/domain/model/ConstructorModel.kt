package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.Constructor

data class ConstructorModel(
    val constructorId: String,
    val url: String? = null,
    val name: String,
    val nationality: String? = null
) {
    companion object {
        fun from(constructor: Constructor): ConstructorModel = ConstructorModel(
            constructorId = constructor.constructorId,
            url = constructor.url,
            name = constructor.name,
            nationality = constructor.nationality
        )
    }
}
