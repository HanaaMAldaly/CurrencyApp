package com.example.currencyapp.domain.interactor

import com.example.currencyapp.domain.model.CurrencyRate
import com.example.currencyapp.domain.repository.HistoryRepository

class CurrencyRateUseCase(private val repository: HistoryRepository) :
    suspend () -> List<CurrencyRate> {
    override suspend fun invoke(): List<CurrencyRate> =
        repository.getLatest()
}
