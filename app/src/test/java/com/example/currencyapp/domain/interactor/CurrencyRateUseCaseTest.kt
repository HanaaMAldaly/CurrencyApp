package com.example.currencyapp.domain.interactor

import com.example.currencyapp.domain.model.CurrencyRate
import com.example.currencyapp.domain.repository.HistoryRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

internal class CurrencyRateUseCaseTest {
    @Test
    fun testCurrencyRateUseCase() {
        val expectedResult = listOf(CurrencyRate("", 5f, ""))
        val repo = mockk<HistoryRepository>()
        val useCase = CurrencyRateUseCase(repo)

        coEvery { repo.getLatest() } returns expectedResult

        runTest {
            assertEquals(expectedResult, useCase.invoke())
        }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
