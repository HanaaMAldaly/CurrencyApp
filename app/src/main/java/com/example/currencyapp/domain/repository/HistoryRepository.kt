package com.example.currencyapp.domain.repository

import com.example.currencyapp.domain.model.CurrencyRate

interface HistoryRepository {
    suspend fun getLatest(): List<CurrencyRate>
    suspend fun getHistory(date: String): List<CurrencyRate>
}
