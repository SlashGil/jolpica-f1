package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.Season

data class SeasonModel(
    val season: Int,
    val url: String? = null
) {
    companion object {
        fun from(season: Season): SeasonModel = SeasonModel(
            season = season.season.toIntOrNull() ?: 0,
            url = season.url
        )
    }
}
