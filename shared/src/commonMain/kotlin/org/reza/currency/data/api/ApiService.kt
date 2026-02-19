package org.reza.currency.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.reza.currency.data.model.response.ExchangeResponse

class ApiService(private val client: HttpClient) {
    suspend fun getLatestExchangeRates(baseCurrency: String = "USD"): ExchangeResponse {
        return client.get("latest/$baseCurrency").body()
    }
}