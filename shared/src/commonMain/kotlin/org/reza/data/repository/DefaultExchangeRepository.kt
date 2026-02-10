package org.reza.data.repository

import org.reza.data.api.ApiService
import org.reza.data.model.response.ExchangeResponse
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DefaultExchangeRepository(
    private val apiService: ApiService
) : ExchangeRepository {

    private var cachedRates: ExchangeResponse? = null
    private var lastFetchedTime: Long = 0L
    private val cacheTimeout = 5 * 60 * 1000 // 5 minutes in milliseconds

    @OptIn(ExperimentalTime::class)
    override suspend fun getRates(base: String): Result<ExchangeResponse> {
        val currentTime = Clock.System.now().toEpochMilliseconds()

        // Check if cache is still valid
        if (cachedRates != null && (currentTime - lastFetchedTime < cacheTimeout)) {
            return Result.success(cachedRates!!)
        }

        // Otherwise, fetch from network with error handling
        return try {
            val response = apiService.getLatestExchangeRates(base)

            cachedRates = response
            lastFetchedTime = currentTime

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}