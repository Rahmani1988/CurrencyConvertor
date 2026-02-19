package org.reza.currency.di

import org.koin.dsl.module
import org.reza.currency.data.repository.DefaultExchangeRepository
import org.reza.currency.data.repository.ExchangeRepository

val repositoryModule = module {
    single<ExchangeRepository> { DefaultExchangeRepository(get()) }
}