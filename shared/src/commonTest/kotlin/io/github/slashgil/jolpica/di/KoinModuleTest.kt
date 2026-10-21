package io.github.slashgil.jolpica.di

import io.github.slashgil.jolpica.JolpicaSdk
import io.github.slashgil.jolpica.client.JolpicaClient
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import io.github.slashgil.jolpica.domain.usecase.GetDriversUseCase
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class KoinModuleTest : KoinTest {

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testKoinModulesResolveSuccessfully() {
        startKoin {
            modules(jolpicaModule)
        }

        val client: JolpicaClient = get()
        val repository: JolpicaRepository = get()
        val getDriversUseCase: GetDriversUseCase = get()
        val sdk: JolpicaSdk = get()

        assertNotNull(client)
        assertNotNull(repository)
        assertNotNull(getDriversUseCase)
        assertNotNull(sdk)
    }
}
