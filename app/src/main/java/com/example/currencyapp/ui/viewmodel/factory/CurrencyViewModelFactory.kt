package com.example.currencyapp.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyapp.domain.interactor.ConvertCurrencyUseCase
import com.example.currencyapp.domain.interactor.CurrencyListUseCase
import com.example.currencyapp.ui.viewmodel.CurrencyViewModel

class CurrencyViewModelFactory(
    private val currencyListUseCase: CurrencyListUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyViewModel(currencyListUseCase, convertCurrencyUseCase) as T
    }
}
