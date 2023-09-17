package com.example.currencyapp.data.remote.mapper

import com.example.currencyapp.data.remote.response.ConvertCurrencyResponse
import com.example.currencyapp.data.remote.response.LatestCurrencyRatesResponse
import com.example.currencyapp.domain.model.Currency
import com.example.currencyapp.domain.model.CurrencyRate

fun LatestCurrencyRatesResponse.map():
    List<Currency> =
    this.rates.flatMap { listOf(Currency(it.code)) }

fun LatestCurrencyRatesResponse.mapToRates():
    List<CurrencyRate> =
    this.rates.flatMap {
        listOf(
            CurrencyRate(
                it.code ?: "",
                it.value ?: 0f,
                this.base,
            ),
        )
    }

fun ConvertCurrencyResponse.map(): Float =
    this.amount ?: 0f
