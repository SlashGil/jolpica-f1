package io.github.slashgil.jolpica.di

import io.github.slashgil.jolpica.data.repository.DefaultJolpicaRepository
import io.github.slashgil.jolpica.domain.repository.JolpicaRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<JolpicaRepository> { DefaultJolpicaRepository(client = get()) }
}
