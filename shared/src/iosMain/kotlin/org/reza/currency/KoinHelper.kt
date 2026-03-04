package org.reza.currency

import org.koin.mp.KoinPlatform
import org.reza.currency.viewmodel.ExchangeViewModel

fun getExchangeViewModel(): ExchangeViewModel = KoinPlatform.getKoin().get()