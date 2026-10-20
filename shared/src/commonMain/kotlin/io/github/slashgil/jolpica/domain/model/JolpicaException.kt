package io.github.slashgil.jolpica.domain.model

sealed class JolpicaException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {
    class NetworkException(cause: Throwable) : JolpicaException("Network request failed", cause)
    class ApiException(val statusCode: Int, message: String) : JolpicaException("API error ($statusCode): $message")
    class ParseException(cause: Throwable) : JolpicaException("Failed to parse response", cause)
    class UnknownException(cause: Throwable) : JolpicaException("An unknown error occurred", cause)
}
