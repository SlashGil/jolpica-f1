package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.ErgastResponse
import io.github.slashgil.jolpica.model.MRData

data class Page<T>(
    val items: List<T>,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val url: String? = null
) {
    companion object {
        fun <T> from(mrData: MRData, items: List<T>): Page<T> = Page(
            items = items,
            limit = mrData.limit?.toIntOrNull() ?: 0,
            offset = mrData.offset?.toIntOrNull() ?: 0,
            total = mrData.total?.toIntOrNull() ?: items.size,
            url = mrData.url
        )

        fun fromSeasons(response: ErgastResponse): Page<SeasonModel> {
            val items = response.mrData.seasonTable?.seasons?.map { SeasonModel.from(it) } ?: emptyList()
            return from(response.mrData, items)
        }

        fun fromCircuits(response: ErgastResponse): Page<CircuitModel> {
            val items = response.mrData.circuitTable?.circuits?.map { CircuitModel.from(it) } ?: emptyList()
            return from(response.mrData, items)
        }

        fun fromConstructors(response: ErgastResponse): Page<ConstructorModel> {
            val items = response.mrData.constructorTable?.constructors?.map { ConstructorModel.from(it) } ?: emptyList()
            return from(response.mrData, items)
        }

        fun fromDrivers(response: ErgastResponse): Page<DriverModel> {
            val items = response.mrData.driverTable?.drivers?.map { DriverModel.from(it) } ?: emptyList()
            return from(response.mrData, items)
        }

        fun fromDriverStandings(response: ErgastResponse): Page<DriverStandingModel> {
            val items = response.mrData.standingsTable?.standingsLists
                ?.flatMap { list -> list.driverStandings?.map { DriverStandingModel.from(it) } ?: emptyList() }
                ?: emptyList()
            return from(response.mrData, items)
        }

        fun fromConstructorStandings(response: ErgastResponse): Page<ConstructorStandingModel> {
            val items = response.mrData.standingsTable?.standingsLists
                ?.flatMap { list -> list.constructorStandings?.map { ConstructorStandingModel.from(it) } ?: emptyList() }
                ?: emptyList()
            return from(response.mrData, items)
        }

        fun fromRaces(response: ErgastResponse): Page<RaceModel> {
            val items = response.mrData.raceTable?.races?.map { RaceModel.from(it) } ?: emptyList()
            return from(response.mrData, items)
        }

        fun fromStatuses(response: ErgastResponse): Page<StatusModel> {
            val items = response.mrData.statusTable?.statusList?.map { StatusModel.from(it) } ?: emptyList()
            return from(response.mrData, items)
        }
    }
}
