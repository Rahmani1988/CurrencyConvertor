package org.reza.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.reza.viewmodel.ExchangeViewModel

val viewmodelModule = module {
    viewModel { ExchangeViewModel(get()) }
}