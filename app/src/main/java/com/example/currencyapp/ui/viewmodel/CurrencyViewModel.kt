package com.example.currencyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyapp.domain.interactor.ConvertCurrencyUseCase
import com.example.currencyapp.domain.interactor.CurrencyListUseCase
import com.example.currencyapp.domain.interactor.DateUseCase
import com.example.currencyapp.domain.model.Currency
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val listCurrencyUseCase: CurrencyListUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val dateUseCase: DateUseCase = DateUseCase(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _currenciesObservable = MutableStateFlow<Result<List<Currency>>?>(null)
    val currencyObservable: StateFlow<Result<List<Currency>>?> = _currenciesObservable

    private val _amount = MutableStateFlow(Result.success(0f))
    val amount: StateFlow<Result<Float>> = _amount

    val fromCurrency = MutableStateFlow<String?>(null)
    val toCurrency = MutableStateFlow<String?>(null)
    val currencyAmount = MutableStateFlow(0f)
    private val listCurrencyErrorHandler = CoroutineExceptionHandler { _, e ->
        _currenciesObservable.value = Result.failure(e)
        listCurrencyErrorDialog.value = true
    }
    val listCurrencyErrorDialog = MutableStateFlow(_currenciesObservable.value?.isFailure)
    val convertCurrencyErrorDialog = MutableStateFlow(_amount.value?.isFailure)

    private val convertCurrencyErrorHandler = CoroutineExceptionHandler { _, e ->
        _amount.value = Result.failure(e)
        convertCurrencyErrorDialog.value = true
    }

    init {
        getCurrencyList()
    }

    fun getCurrencyList() {
        listCurrencyErrorDialog.value = false
        viewModelScope.launch(dispatcher + listCurrencyErrorHandler) {
            _currenciesObservable.emit(Result.success(listCurrencyUseCase.invoke()))
        }
    }

    fun convertCurrency() {
        convertCurrencyErrorDialog.value = false
        viewModelScope.launch(dispatcher + convertCurrencyErrorHandler) {
            _amount.emit(
                Result.success(
                    convertCurrencyUseCase.invoke(
                        fromCurrency.value ?: "",
                        toCurrency.value ?: "",
                        currencyAmount.value,
                        dateUseCase.invoke(0),
                    ),
                ),
            )
        }
    }

    fun onSwipe() {
        val from = fromCurrency.value
        fromCurrency.value = toCurrency.value
        toCurrency.value = from
    }
}
