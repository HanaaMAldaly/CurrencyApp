package com.example.currencyapp.domain.interactor

import com.example.currencyapp.domain.model.CurrencyRate
import com.example.currencyapp.domain.repository.HistoryRepository

class CurrencyHistoryUseCase(private val repository: HistoryRepository) :
    suspend (String) -> List<CurrencyRate> {
    override suspend fun invoke(date: String): List<CurrencyRate> =
        repository.getHistory(date)
}
