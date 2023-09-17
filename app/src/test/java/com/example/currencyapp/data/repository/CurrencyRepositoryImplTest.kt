package com.example.currencyapp.data.repository

import android.content.Context
import com.example.currencyapp.data.remote.network.CurrencyApi
import com.example.currencyapp.data.remote.network.CurrencyCloud
import com.example.currencyapp.data.remote.response.ConvertCurrencyResponse
import com.example.currencyapp.data.remote.response.LatestCurrencyRatesResponse
import com.example.currencyapp.domain.model.Currency
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

internal class CurrencyRepositoryImplTest {
    @Test
    fun testGetCurrencies() {
        val expectedResult = listOf<Currency>()
        val context = mockk<Context>()
        val mockedAPI = mockk<CurrencyApi>()
        val repo = CurrencyRepositoryImpl(context)
        val response = mockk<LatestCurrencyRatesResponse>()
        mockkObject(CurrencyCloud)
        every { CurrencyCloud.getCurrencyAPI(context) } returns mockedAPI
        coEvery { mockedAPI.getLatestCurrencyRates() } returns mockk()
        coEvery { mockedAPI.getLatestCurrencyRates() } returns response
        every { response.rates } returns listOf()
        runTest {
            assertEquals(expectedResult, repo.getCurrencies())
        }
    }

    @Test
    fun testConvertCurrencies() {
        val expectedResult = 5f
        val from = "GPA"
        val to = "TR"
        val amount = 2f
        val date = "13-05-2022"
        val context = mockk<Context>()
        val mockedAPI = mockk<CurrencyApi>()
        val repo = CurrencyRepositoryImpl(context)
        val response = mockk<ConvertCurrencyResponse>()
        mockkObject(CurrencyCloud)
        every { CurrencyCloud.getCurrencyAPI(context) } returns mockedAPI
        coEvery { mockedAPI.convertCurrency(from, to, amount, date) } returns mockk()
        coEvery { mockedAPI.convertCurrency(from, to, amount, date) } returns response
        every { response.amount } returns expectedResult
        runTest {
            assertEquals(expectedResult, repo.convertCurrency(from, to, amount, date))
        }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
