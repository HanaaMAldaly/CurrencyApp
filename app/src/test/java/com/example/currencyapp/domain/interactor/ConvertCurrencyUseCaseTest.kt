package com.example.currencyapp.domain.interactor

import com.example.currencyapp.domain.repository.CurrencyRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

internal class ConvertCurrencyUseCaseTest {
    @Test
    fun testConvertCurrencyUseCase() {
        val expectedResult = 5f
        val from = "GPA"
        val to = "TR"
        val amount = 2f
        val date = "13-05-2022"
        val repo = mockk<CurrencyRepository>()
        val useCase = ConvertCurrencyUseCase(repo)

        coEvery { repo.convertCurrency(from, to, amount, date) } returns expectedResult

        runTest {
            assertEquals(expectedResult, useCase.invoke(from, to, amount, date))
        }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
