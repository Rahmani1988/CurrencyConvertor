package org.reza.currency.data.repository

import org.reza.currency.data.model.response.ExchangeResponse

interface ExchangeRepository {
    suspend fun getRates(base: String): Result<ExchangeResponse>
}