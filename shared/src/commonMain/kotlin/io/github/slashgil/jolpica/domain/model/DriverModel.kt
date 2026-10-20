package io.github.slashgil.jolpica.domain.model

import io.github.slashgil.jolpica.model.Driver

data class DriverModel(
    val driverId: String,
    val permanentNumber: Int? = null,
    val code: String? = null,
    val url: String? = null,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String? = null,
    val nationality: String? = null
) {
    val fullName: String get() = "$givenName $familyName"

    companion object {
        fun from(driver: Driver): DriverModel = DriverModel(
            driverId = driver.driverId,
            permanentNumber = driver.permanentNumber?.toIntOrNull(),
            code = driver.code,
            url = driver.url,
            givenName = driver.givenName,
            familyName = driver.familyName,
            dateOfBirth = driver.dateOfBirth,
            nationality = driver.nationality
        )
    }
}
