package com.example.currencyapp.data.repository

import android.content.Context
import com.example.currencyapp.data.remote.mapper.mapToRates
import com.example.currencyapp.data.remote.network.CurrencyCloud
import com.example.currencyapp.domain.model.CurrencyRate
import com.example.currencyapp.domain.repository.HistoryRepository

class HistoryRepositoryImpl(private val context: Context) : HistoryRepository {
    override suspend fun getHistory(date: String): List<CurrencyRate> =
        CurrencyCloud
            .getCurrencyAPI(context)
            .getHistory(date)
            .mapToRates()

    override suspend fun getLatest(): List<CurrencyRate> =
        CurrencyCloud
            .getCurrencyAPI(context)
            .getLatestCurrencyRates()
            .mapToRates()
}
