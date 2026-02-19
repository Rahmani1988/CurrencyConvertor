package org.reza.currency.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.reza.currency.shared.BuildKonfig
import org.reza.currency.data.api.ApiService

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
            defaultRequest {
                url("${BuildKonfig.BASE_URL}${BuildKonfig.EXCHANGE_API_KEY}/")
            }
        }
    }

    single { ApiService(get()) }
}