package com.example.currencyapp.data.repository

import android.content.Context
import com.example.currencyapp.data.remote.mapper.map
import com.example.currencyapp.data.remote.network.CurrencyCloud
import com.example.currencyapp.domain.model.Currency
import com.example.currencyapp.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(private val context: Context) : CurrencyRepository {
    override suspend fun getCurrencies(): List<Currency> =
        CurrencyCloud
            .getCurrencyAPI(context)
            .getLatestCurrencyRates()
            .map()

    override suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Float,
        date: String,
    ): Float {
        return CurrencyCloud
            .getCurrencyAPI(context)
            .convertCurrency(from, to, amount, date)
            .map()
    }
}
