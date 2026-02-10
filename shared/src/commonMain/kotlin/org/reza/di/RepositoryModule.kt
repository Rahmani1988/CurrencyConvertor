package org.reza.di

import org.koin.dsl.module
import org.reza.data.repository.DefaultExchangeRepository
import org.reza.data.repository.ExchangeRepository

val repositoryModule = module {
    single<ExchangeRepository> { DefaultExchangeRepository(get()) }
}