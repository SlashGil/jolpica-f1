package io.github.slashgil.jolpica.domain.model

data class Page<T>(
    val items: List<T>,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val url: String? = null
)
