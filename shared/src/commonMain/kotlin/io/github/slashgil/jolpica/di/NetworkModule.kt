package io.github.slashgil.jolpica.di

import io.github.slashgil.jolpica.client.JolpicaClient
import io.github.slashgil.jolpica.client.createDefaultHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> { createDefaultHttpClient() }
    single<JolpicaClient> { JolpicaClient(httpClient = get()) }
}
