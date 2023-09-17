package com.example.currencyapp.domain.interactor

import com.example.currencyapp.domain.repository.CurrencyRepository

class ConvertCurrencyUseCase(private val repository: CurrencyRepository) : suspend (
    String,
    String,
    Float,
    String,
) -> Float {
    override suspend fun invoke(from: String, to: String, amount: Float, date: String): Float {
        return repository.convertCurrency(from, to, amount, date)
    }
}
