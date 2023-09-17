package com.example.currencyapp.domain.repository

import com.example.currencyapp.domain.model.Currency

interface CurrencyRepository {
    suspend fun getCurrencies(): List<Currency>
    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Float,
        date: String,
    ): Float
}
