package com.example.currencyapp.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyapp.domain.interactor.CurrencyHistoryUseCase
import com.example.currencyapp.domain.interactor.CurrencyRateUseCase
import com.example.currencyapp.ui.viewmodel.DetailsViewModel

class DetailsViewModelFactory(
    private val historyUseCase: CurrencyHistoryUseCase,
    private val currencyRateUseCase: CurrencyRateUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(historyUseCase, currencyRateUseCase) as T
    }
}
