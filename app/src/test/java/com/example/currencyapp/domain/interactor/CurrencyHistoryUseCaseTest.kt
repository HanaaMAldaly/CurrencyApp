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

internal class CurrencyHistoryUseCaseTest {
    @Test
    fun testCurrencyHistoryUseCase() {
        val expectedResult = listOf(CurrencyRate("", 5f, ""))
        val date = "13-05-2022"
        val repo = mockk<HistoryRepository>()
        val useCase = CurrencyHistoryUseCase(repo)

        coEvery { repo.getHistory(date) } returns expectedResult

        runTest {
            assertEquals(expectedResult, useCase.invoke(date))
        }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
