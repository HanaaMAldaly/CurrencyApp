package com.example.currencyapp.ui.viewmodel

import com.example.currencyapp.domain.interactor.ConvertCurrencyUseCase
import com.example.currencyapp.domain.interactor.CurrencyListUseCase
import com.example.currencyapp.domain.interactor.DateUseCase
import com.example.currencyapp.domain.model.Currency
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Test
import java.io.IOException

internal class CurrencyViewModelTest {
    @Test
    fun test_ListCurrency() {
        val listCurrencyUseCase = mockk<CurrencyListUseCase>()
        val convertCurrencyUseCase = mockk<ConvertCurrencyUseCase>()
        coEvery { listCurrencyUseCase.invoke() } returns listOf()
        val viewModel =
            CurrencyViewModel(listCurrencyUseCase, convertCurrencyUseCase, DateUseCase())

        coVerify { listCurrencyUseCase.invoke() }
        assertFalse(viewModel.listCurrencyErrorDialog.value ?: true)
    }

    @Test
    fun test_ListCurrency_whenErrorOccur_expectErrorDialogBeShown() {
        val exception = IOException()
        val listCurrencyUseCase = mockk<CurrencyListUseCase>()
        val convertCurrencyUseCase = mockk<ConvertCurrencyUseCase>()
        coEvery { listCurrencyUseCase.invoke() } throws exception
        val viewModel =
            CurrencyViewModel(listCurrencyUseCase, convertCurrencyUseCase, DateUseCase())

        coVerify { listCurrencyUseCase.invoke() }
        assertTrue(viewModel.listCurrencyErrorDialog.value ?: false)
        assertEquals(Result.failure<List<Currency>>(exception), viewModel.currencyObservable.value)
    }

    @Test
    fun test_convertCurrency() {
        val result = 5f
        val from = "GPA"
        val to = "TR"
        val amount = 2f
        val date = DateUseCase().invoke(0)
        val listCurrencyUseCase = mockk<CurrencyListUseCase>()
        val convertCurrencyUseCase = mockk<ConvertCurrencyUseCase>()
        coEvery { convertCurrencyUseCase.invoke(from, to, amount, date) } returns result
        val viewModel =
            CurrencyViewModel(listCurrencyUseCase, convertCurrencyUseCase, DateUseCase())
        viewModel.toCurrency.value = to
        viewModel.fromCurrency.value = from
        viewModel.currencyAmount.value = amount
        viewModel.convertCurrency()

        coVerify { convertCurrencyUseCase.invoke(from, to, amount, date) }
        assertFalse(viewModel.convertCurrencyErrorDialog.value ?: true)
    }

    @Test
    fun test_convertCurrency_whenErrorOccur_expectErrorDialogBeShown() {
        val exception = IOException()
        val from = "GPA"
        val to = "TR"
        val amount = 2f
        val date = DateUseCase().invoke(0)
        val listCurrencyUseCase = mockk<CurrencyListUseCase>()
        val convertCurrencyUseCase = mockk<ConvertCurrencyUseCase>()
        coEvery { convertCurrencyUseCase.invoke(from, to, amount, date) } throws exception
        val viewModel =
            CurrencyViewModel(listCurrencyUseCase, convertCurrencyUseCase, DateUseCase())
        viewModel.toCurrency.value = to
        viewModel.fromCurrency.value = from
        viewModel.currencyAmount.value = amount

        viewModel.convertCurrency()

        coVerify { convertCurrencyUseCase.invoke(from, to, amount, date) }
        assertTrue(viewModel.convertCurrencyErrorDialog.value ?: false)
        assertEquals(Result.failure<List<Currency>>(exception), viewModel.amount.value)
    }

    @Test
    fun testSwipe() {
        val from = "GPA"
        val to = "TR"
        val viewModel =
            CurrencyViewModel(mockk(relaxed = true), mockk(relaxed = true))
        viewModel.fromCurrency.value = from
        viewModel.toCurrency.value = to
        viewModel.onSwipe()

        assertEquals(from, viewModel.toCurrency.value)
        assertEquals(to, viewModel.fromCurrency.value)
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
