package org.reza.currency.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.reza.currency.viewmodel.ExchangeViewModel

val viewmodelModule = module {
    viewModel { ExchangeViewModel(get()) }
}