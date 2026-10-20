package io.github.slashgil.jolpica.domain.model

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
}
