package com.example.currencyapp.domain.interactor

import com.example.currencyapp.domain.model.Currency
import com.example.currencyapp.domain.repository.CurrencyRepository

class CurrencyListUseCase(private val repo: CurrencyRepository) : suspend () -> List<Currency> {
    override suspend fun invoke(): List<Currency> {
        return repo.getCurrencies()
    }
}
