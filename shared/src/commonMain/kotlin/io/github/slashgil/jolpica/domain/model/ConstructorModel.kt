package io.github.slashgil.jolpica.domain.model

data class ConstructorModel(
    val constructorId: String,
    val url: String? = null,
    val name: String,
    val nationality: String? = null
)
