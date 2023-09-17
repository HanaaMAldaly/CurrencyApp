package com.example.currencyapp.domain.interactor

import com.example.currencyapp.domain.model.Currency
import com.example.currencyapp.domain.repository.CurrencyRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

internal class CurrencyListUseCaseTest {
    @Test
    fun testCurrencyListUseCase() {
        val expectedResult = listOf(Currency(""))
        val repo = mockk<CurrencyRepository>()
        val useCase = CurrencyListUseCase(repo)

        coEvery { repo.getCurrencies() } returns expectedResult

        runTest {
            assertEquals(expectedResult, useCase.invoke())
        }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
