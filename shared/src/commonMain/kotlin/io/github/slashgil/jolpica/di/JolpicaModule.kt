package io.github.slashgil.jolpica.di

import io.github.slashgil.jolpica.JolpicaSdk
import org.koin.dsl.module

val jolpicaModule = module {
    includes(networkModule, repositoryModule, useCaseModule)
    single { JolpicaSdk(client = get(), repository = get()) }
}
