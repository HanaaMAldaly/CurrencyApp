package com.example.currencyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyapp.domain.interactor.CurrencyHistoryUseCase
import com.example.currencyapp.domain.interactor.CurrencyRateUseCase
import com.example.currencyapp.domain.interactor.DateUseCase
import com.example.currencyapp.domain.model.CurrencyRate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val historyUseCase: CurrencyHistoryUseCase,
    private val currencyRateUseCase: CurrencyRateUseCase,
    private val dateUseCase: DateUseCase = DateUseCase(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _history = MutableStateFlow<Result<List<CurrencyRate>>?>(null)
    val history: StateFlow<Result<List<CurrencyRate>>?> = _history

    private val _rate = MutableStateFlow<Result<List<CurrencyRate>>?>(null)
    val rate: StateFlow<Result<List<CurrencyRate>>?> = _rate

    private val historyErrorHandler = CoroutineExceptionHandler { _, e ->
        _history.value = Result.failure(e)
        historyErrorDialog.value = true
    }
    private val rateCurrencyErrorHandler = CoroutineExceptionHandler { _, e ->
        _rate.value = Result.failure(e)
        rateErrorDialog.value = true
    }

    val historyErrorDialog = MutableStateFlow(_history.value?.isFailure)
    val rateErrorDialog = MutableStateFlow(_rate.value?.isFailure)

    init {
        getRateCurrency()
        getHistory()
    }

    fun getHistory() {
        historyErrorDialog.value = false
        viewModelScope.launch(dispatcher + historyErrorHandler) {
            val day1 = async { historyUseCase.invoke(dateUseCase.invoke(0)) }
            val day2 = async { historyUseCase.invoke(dateUseCase.invoke(-1)) }
            val day3 = async { historyUseCase.invoke(dateUseCase.invoke(-2)) }
            _history.emit(
                Result
                    .success(
                        awaitAll(day1, day2, day3)
                            .flatten(),
                    ),
            )
        }
    }

    fun getRateCurrency() {
        rateErrorDialog.value = false
        viewModelScope.launch(dispatcher + rateCurrencyErrorHandler) {
            _rate.emit(
                Result
                    .success(
                        currencyRateUseCase.invoke(),
                    ),
            )
        }
    }
}
