package org.reza.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import org.reza.data.model.response.ExchangeResponse

class ApiService(private val client: HttpClient) {
    suspend fun getLatestExchangeRates(baseCurrency: String = "USD"): ExchangeResponse {
        return client.get("latest/$baseCurrency").body()
    }
}