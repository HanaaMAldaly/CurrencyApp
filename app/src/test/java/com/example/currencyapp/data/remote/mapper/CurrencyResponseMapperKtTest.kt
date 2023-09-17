package com.example.currencyapp.data.remote.mapper

import com.example.currencyapp.data.remote.response.ConvertCurrencyResponse
import com.example.currencyapp.data.remote.response.LatestCurrencyRatesResponse
import com.example.currencyapp.data.remote.response.RatesResponse
import com.example.currencyapp.domain.model.Currency
import com.example.currencyapp.domain.model.CurrencyRate
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class CurrencyResponseMapperKtTest {
    @Test
    fun testLatestCurrencyRatesResponseMap() {
        val expectedResult = listOf(Currency("GPA"))
        val response = LatestCurrencyRatesResponse(
            true,
            null,
            "",
            listOf(RatesResponse("GPA", 5f)),
        )
        val result = response.map()

        assertEquals(expectedResult, result)
    }

    @Test
    fun testLatestCurrencyRatesResponseMapToRate() {
        val expectedResult = listOf(CurrencyRate("GPA", 5f, "UR"))
        val response = LatestCurrencyRatesResponse(
            true,
            null,
            "UR",
            listOf(RatesResponse("GPA", 5f)),
        )
        val result = response.mapToRates()

        assertEquals(expectedResult, result)
    }

    @Test
    fun testConvertCurrencyResponseMap() {
        val expectedResult = 5f
        val response = ConvertCurrencyResponse(
            true,
            null,
            5f,
        )
        val result = response.map()

        assertEquals(expectedResult, result)
    }
}
