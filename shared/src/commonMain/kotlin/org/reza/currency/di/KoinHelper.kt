package org.reza.currency.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        networkModule,
        repositoryModule,
        viewmodelModule
    )
}

// Special helper JUST for iOS/Swift
fun initKoinIos() = initKoin {}