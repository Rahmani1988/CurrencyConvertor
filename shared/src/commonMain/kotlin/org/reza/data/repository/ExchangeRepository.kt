package org.reza.data.repository

import org.reza.data.model.response.ExchangeResponse

interface ExchangeRepository {
    suspend fun getRates(base: String): Result<ExchangeResponse>
}