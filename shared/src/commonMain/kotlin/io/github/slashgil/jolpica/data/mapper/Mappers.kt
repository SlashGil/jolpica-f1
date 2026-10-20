package io.github.slashgil.jolpica.data.mapper

import io.github.slashgil.jolpica.domain.model.AverageSpeedModel
import io.github.slashgil.jolpica.domain.model.CircuitModel
import io.github.slashgil.jolpica.domain.model.ConstructorModel
import io.github.slashgil.jolpica.domain.model.ConstructorStandingModel
import io.github.slashgil.jolpica.domain.model.DriverModel
import io.github.slashgil.jolpica.domain.model.DriverStandingModel
import io.github.slashgil.jolpica.domain.model.FastestLapModel
import io.github.slashgil.jolpica.domain.model.LapModel
import io.github.slashgil.jolpica.domain.model.LocationModel
import io.github.slashgil.jolpica.domain.model.Page
import io.github.slashgil.jolpica.domain.model.PitStopModel
import io.github.slashgil.jolpica.domain.model.QualifyingResultModel
import io.github.slashgil.jolpica.domain.model.RaceModel
import io.github.slashgil.jolpica.domain.model.ResultModel
import io.github.slashgil.jolpica.domain.model.ScheduleTimeModel
import io.github.slashgil.jolpica.domain.model.SeasonModel
import io.github.slashgil.jolpica.domain.model.SprintResultModel
import io.github.slashgil.jolpica.domain.model.StatusModel
import io.github.slashgil.jolpica.domain.model.TimeInfoModel
import io.github.slashgil.jolpica.domain.model.TimingModel
import io.github.slashgil.jolpica.model.AverageSpeed
import io.github.slashgil.jolpica.model.Circuit
import io.github.slashgil.jolpica.model.Constructor
import io.github.slashgil.jolpica.model.ConstructorStanding
import io.github.slashgil.jolpica.model.Driver
import io.github.slashgil.jolpica.model.DriverStanding
import io.github.slashgil.jolpica.model.ErgastResponse
import io.github.slashgil.jolpica.model.FastestLap
import io.github.slashgil.jolpica.model.Lap
import io.github.slashgil.jolpica.model.Location
import io.github.slashgil.jolpica.model.MRData
import io.github.slashgil.jolpica.model.PitStop
import io.github.slashgil.jolpica.model.QualifyingResult
import io.github.slashgil.jolpica.model.Race
import io.github.slashgil.jolpica.model.Result
import io.github.slashgil.jolpica.model.ScheduleTime
import io.github.slashgil.jolpica.model.Season
import io.github.slashgil.jolpica.model.SprintResult
import io.github.slashgil.jolpica.model.Status
import io.github.slashgil.jolpica.model.TimeInfo
import io.github.slashgil.jolpica.model.Timing

fun Driver.toDomain(): DriverModel = DriverModel(
    driverId = driverId,
    permanentNumber = permanentNumber?.toIntOrNull(),
    code = code,
    url = url,
    givenName = givenName,
    familyName = familyName,
    dateOfBirth = dateOfBirth,
    nationality = nationality
)

fun Constructor.toDomain(): ConstructorModel = ConstructorModel(
    constructorId = constructorId,
    url = url,
    name = name,
    nationality = nationality
)

fun Location.toDomain(): LocationModel = LocationModel(
    lat = lat?.toDoubleOrNull(),
    long = long?.toDoubleOrNull(),
    locality = locality,
    country = country
)

fun Circuit.toDomain(): CircuitModel = CircuitModel(
    circuitId = circuitId,
    url = url,
    circuitName = circuitName,
    location = location?.toDomain()
)

fun Season.toDomain(): SeasonModel = SeasonModel(
    season = season.toIntOrNull() ?: 0,
    url = url
)

fun Status.toDomain(): StatusModel = StatusModel(
    statusId = statusId.toIntOrNull() ?: 0,
    count = count.toIntOrNull() ?: 0,
    status = status
)

fun ScheduleTime.toDomain(): ScheduleTimeModel = ScheduleTimeModel(
    date = date,
    time = time
)

fun TimeInfo.toDomain(): TimeInfoModel = TimeInfoModel(
    millis = millis?.toLongOrNull(),
    time = time
)

fun AverageSpeed.toDomain(): AverageSpeedModel = AverageSpeedModel(
    units = units,
    speed = speed?.toDoubleOrNull()
)

fun FastestLap.toDomain(): FastestLapModel = FastestLapModel(
    rank = rank?.toIntOrNull(),
    lap = lap?.toIntOrNull(),
    time = time?.toDomain(),
    averageSpeed = averageSpeed?.toDomain()
)

fun Result.toDomain(): ResultModel = ResultModel(
    number = number?.toIntOrNull(),
    position = position.toIntOrNull() ?: 0,
    positionText = positionText,
    points = points?.toDoubleOrNull() ?: 0.0,
    driver = driver.toDomain(),
    constructor = constructor.toDomain(),
    grid = grid?.toIntOrNull(),
    laps = laps?.toIntOrNull(),
    status = status,
    time = time?.toDomain(),
    fastestLap = fastestLap?.toDomain()
)

fun SprintResult.toDomain(): SprintResultModel = SprintResultModel(
    number = number?.toIntOrNull(),
    position = position.toIntOrNull() ?: 0,
    positionText = positionText,
    points = points?.toDoubleOrNull() ?: 0.0,
    driver = driver.toDomain(),
    constructor = constructor.toDomain(),
    grid = grid?.toIntOrNull(),
    laps = laps?.toIntOrNull(),
    status = status,
    time = time?.toDomain(),
    fastestLap = fastestLap?.toDomain()
)

fun QualifyingResult.toDomain(): QualifyingResultModel = QualifyingResultModel(
    number = number?.toIntOrNull(),
    position = position.toIntOrNull() ?: 0,
    driver = driver.toDomain(),
    constructor = constructor.toDomain(),
    q1 = q1,
    q2 = q2,
    q3 = q3
)

fun PitStop.toDomain(): PitStopModel = PitStopModel(
    driverId = driverId,
    lap = lap.toIntOrNull() ?: 0,
    stop = stop.toIntOrNull() ?: 0,
    time = time,
    duration = duration
)

fun Timing.toDomain(): TimingModel = TimingModel(
    driverId = driverId,
    position = position.toIntOrNull() ?: 0,
    time = time
)

fun Lap.toDomain(): LapModel = LapModel(
    number = number.toIntOrNull() ?: 0,
    timings = timings.map { it.toDomain() }
)

fun Race.toDomain(): RaceModel = RaceModel(
    season = season.toIntOrNull() ?: 0,
    round = round.toIntOrNull() ?: 0,
    url = url,
    raceName = raceName,
    circuit = circuit?.toDomain(),
    date = date,
    time = time,
    firstPractice = firstPractice?.toDomain(),
    secondPractice = secondPractice?.toDomain(),
    thirdPractice = thirdPractice?.toDomain(),
    qualifying = qualifying?.toDomain(),
    sprint = sprint?.toDomain(),
    results = results?.map { it.toDomain() },
    qualifyingResults = qualifyingResults?.map { it.toDomain() },
    sprintResults = sprintResults?.map { it.toDomain() },
    pitStops = pitStops?.map { it.toDomain() },
    laps = laps?.map { it.toDomain() }
)

fun DriverStanding.toDomain(): DriverStandingModel = DriverStandingModel(
    position = position.toIntOrNull() ?: 0,
    positionText = positionText,
    points = points.toDoubleOrNull() ?: 0.0,
    wins = wins.toIntOrNull() ?: 0,
    driver = driver.toDomain(),
    constructors = constructors.map { it.toDomain() }
)

fun ConstructorStanding.toDomain(): ConstructorStandingModel = ConstructorStandingModel(
    position = position.toIntOrNull() ?: 0,
    positionText = positionText,
    points = points.toDoubleOrNull() ?: 0.0,
    wins = wins.toIntOrNull() ?: 0,
    constructor = constructor.toDomain()
)

private fun <T> MRData.toPage(items: List<T>): Page<T> = Page(
    items = items,
    limit = limit?.toIntOrNull() ?: 0,
    offset = offset?.toIntOrNull() ?: 0,
    total = total?.toIntOrNull() ?: items.size,
    url = url
)

fun ErgastResponse.toSeasonsPage(): Page<SeasonModel> {
    val items = mrData.seasonTable?.seasons?.map { it.toDomain() } ?: emptyList()
    return mrData.toPage(items)
}

fun ErgastResponse.toCircuitsPage(): Page<CircuitModel> {
    val items = mrData.circuitTable?.circuits?.map { it.toDomain() } ?: emptyList()
    return mrData.toPage(items)
}

fun ErgastResponse.toConstructorsPage(): Page<ConstructorModel> {
    val items = mrData.constructorTable?.constructors?.map { it.toDomain() } ?: emptyList()
    return mrData.toPage(items)
}

fun ErgastResponse.toDriversPage(): Page<DriverModel> {
    val items = mrData.driverTable?.drivers?.map { it.toDomain() } ?: emptyList()
    return mrData.toPage(items)
}

fun ErgastResponse.toDriverStandingsPage(): Page<DriverStandingModel> {
    val items = mrData.standingsTable?.standingsLists
        ?.flatMap { list -> list.driverStandings?.map { it.toDomain() } ?: emptyList() }
        ?: emptyList()
    return mrData.toPage(items)
}

fun ErgastResponse.toConstructorStandingsPage(): Page<ConstructorStandingModel> {
    val items = mrData.standingsTable?.standingsLists
        ?.flatMap { list -> list.constructorStandings?.map { it.toDomain() } ?: emptyList() }
        ?: emptyList()
    return mrData.toPage(items)
}

fun ErgastResponse.toRacesPage(): Page<RaceModel> {
    val items = mrData.raceTable?.races?.map { it.toDomain() } ?: emptyList()
    return mrData.toPage(items)
}

fun ErgastResponse.toStatusesPage(): Page<StatusModel> {
    val items = mrData.statusTable?.statusList?.map { it.toDomain() } ?: emptyList()
    return mrData.toPage(items)
}
