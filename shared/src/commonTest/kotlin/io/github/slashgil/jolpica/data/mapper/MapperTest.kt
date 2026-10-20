package io.github.slashgil.jolpica.data.mapper

import io.github.slashgil.jolpica.domain.model.CircuitModel
import io.github.slashgil.jolpica.domain.model.DriverModel
import io.github.slashgil.jolpica.domain.model.ResultModel
import io.github.slashgil.jolpica.model.Circuit
import io.github.slashgil.jolpica.model.Constructor
import io.github.slashgil.jolpica.model.Driver
import io.github.slashgil.jolpica.model.Location
import io.github.slashgil.jolpica.model.Result
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MapperTest {

    @Test
    fun testDriverFromDto() {
        val dto = Driver(
            driverId = "max_verstappen",
            permanentNumber = "33",
            code = "VER",
            url = "http://example.com",
            givenName = "Max",
            familyName = "Verstappen",
            dateOfBirth = "1997-09-30",
            nationality = "Dutch"
        )

        val domain = DriverModel.from(dto)

        assertEquals("max_verstappen", domain.driverId)
        assertEquals(33, domain.permanentNumber)
        assertEquals("VER", domain.code)
        assertEquals("Max Verstappen", domain.fullName)
        assertEquals("Dutch", domain.nationality)
    }

    @Test
    fun testCircuitFromDto() {
        val dto = Circuit(
            circuitId = "silverstone",
            circuitName = "Silverstone Circuit",
            location = Location(
                lat = "52.0786",
                long = "-1.01694",
                locality = "Silverstone",
                country = "UK"
            )
        )

        val domain = CircuitModel.from(dto)

        assertEquals("silverstone", domain.circuitId)
        assertEquals("Silverstone Circuit", domain.circuitName)
        assertNotNull(domain.location)
        assertEquals(52.0786, domain.location.lat)
        assertEquals(-1.01694, domain.location.long)
        assertEquals("UK", domain.location.country)
    }

    @Test
    fun testResultFromDto() {
        val dto = Result(
            number = "1",
            position = "1",
            positionText = "1",
            points = "25",
            driver = Driver(driverId = "verstappen", givenName = "Max", familyName = "Verstappen"),
            constructor = Constructor(constructorId = "red_bull", name = "Red Bull"),
            grid = "1",
            laps = "52",
            status = "Finished"
        )

        val domain = ResultModel.from(dto)

        assertEquals(1, domain.position)
        assertEquals(25.0, domain.points)
        assertEquals(1, domain.grid)
        assertEquals(52, domain.laps)
        assertEquals("Finished", domain.status)
        assertEquals("verstappen", domain.driver.driverId)
        assertEquals("red_bull", domain.constructor.constructorId)
    }
}
