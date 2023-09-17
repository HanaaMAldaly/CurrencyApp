package com.example.currencyapp.ui.viewmodel

import com.example.currencyapp.domain.interactor.CurrencyHistoryUseCase
import com.example.currencyapp.domain.interactor.CurrencyRateUseCase
import com.example.currencyapp.domain.interactor.DateUseCase
import com.example.currencyapp.domain.model.CurrencyRate
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Test
import java.io.IOException

internal class DetailsViewModelTest {
    @Test
    fun test_GetHistory() {
        val historyUseCase = mockk<CurrencyHistoryUseCase>()
        val rateUseCase = mockk<CurrencyRateUseCase>()
        coEvery { historyUseCase.invoke(any()) } returns listOf()
        val viewModel =
            DetailsViewModel(historyUseCase, rateUseCase, DateUseCase())

        coVerify { historyUseCase.invoke(DateUseCase().invoke(0)) }
        coVerify { historyUseCase.invoke(DateUseCase().invoke(-1)) }
        coVerify { historyUseCase.invoke(DateUseCase().invoke(-2)) }
        assertEquals(Result.success(listOf<CurrencyRate>()), viewModel.history.value)
        assertFalse(viewModel.historyErrorDialog.value ?: true)
    }

    @Test
    fun test_GetHistory_whenErrorOccur_expectErrorDialog() {
        val exception = IOException()
        val historyUseCase = mockk<CurrencyHistoryUseCase>()
        val rateUseCase = mockk<CurrencyRateUseCase>()
        coEvery { historyUseCase.invoke(any()) } throws exception

        val viewModel =
            DetailsViewModel(historyUseCase, rateUseCase, DateUseCase(), TestCoroutineDispatcher())

        assertTrue(viewModel.historyErrorDialog.value ?: false)
        assertEquals(Result.failure<CurrencyRate>(exception), viewModel.history.value)
    }

    @Test
    fun testGetRates() {
        val historyUseCase = mockk<CurrencyHistoryUseCase>()
        val rateUseCase = mockk<CurrencyRateUseCase>()
        coEvery { rateUseCase.invoke() } returns listOf()

        val viewModel = DetailsViewModel(historyUseCase, rateUseCase, DateUseCase())

        coVerify { rateUseCase.invoke() }
        assertEquals(Result.success(listOf<CurrencyRate>()), viewModel.rate.value)
        assertFalse(viewModel.rateErrorDialog.value ?: true)
    }

    @Test()
    fun test_GetRates_whenErrorOccur_expectShowErrorDialog() {
        val exception = IOException()
        val historyUseCase = mockk<CurrencyHistoryUseCase>()
        val rateUseCase = mockk<CurrencyRateUseCase>()
        coEvery { rateUseCase.invoke() } throws exception

        val viewModel =
            DetailsViewModel(historyUseCase, rateUseCase, DateUseCase(), TestCoroutineDispatcher())
        assertTrue(viewModel.rateErrorDialog.value ?: false)
        assertEquals(Result.failure<CurrencyRate>(exception), viewModel.rate.value)
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
