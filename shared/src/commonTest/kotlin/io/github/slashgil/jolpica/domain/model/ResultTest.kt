package io.github.slashgil.jolpica.domain.model

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ResultTest {

    @Test
    fun testResultStates() {
        val success: Result<String> = Result.Success("F1")
        val error: Result<String> = Result.Error(IllegalStateException("Failed"))
        val loading: Result<String> = Result.Loading

        assertTrue(success.isSuccess)
        assertFalse(success.isError)
        assertFalse(success.isLoading)
        assertEquals("F1", success.getOrNull())

        assertTrue(error.isError)
        assertFalse(error.isSuccess)
        assertFalse(error.isLoading)
        assertTrue(error.exceptionOrNull() is IllegalStateException)

        assertTrue(loading.isLoading)
        assertFalse(loading.isSuccess)
        assertFalse(loading.isError)
    }

    @Test
    fun testResultMap() {
        val success: Result<Int> = Result.Success(10)
        val mapped = success.map { it * 2 }

        assertEquals(20, mapped.getOrNull())
    }

    @Test
    fun testAsResultFlowSuccess() = runTest {
        val block: suspend () -> String = { "Max Verstappen" }
        val emissions = block.asResultFlow().toList()

        assertEquals(2, emissions.size)
        assertTrue(emissions[0] is Result.Loading)
        assertTrue(emissions[1] is Result.Success)
        assertEquals("Max Verstappen", (emissions[1] as Result.Success).data)
    }

    @Test
    fun testAsResultFlowError() = runTest {
        val block: suspend () -> String = { throw IllegalArgumentException("Invalid ID") }
        val emissions = block.asResultFlow().toList()

        assertEquals(2, emissions.size)
        assertTrue(emissions[0] is Result.Loading)
        assertTrue(emissions[1] is Result.Error)
        assertTrue((emissions[1] as Result.Error).exception is IllegalArgumentException)
    }
}
