package com.example.currencyapp.data.remote.network

import android.content.Context
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit

internal class CurrencyCloudTest {
    @Test
    fun testCurrencyCloud_expectReturnCurrencyAPI() {
        val currencyApi = mockk<CurrencyApi>()
        val retrofit = mockk<Retrofit>()
        val context = mockk<Context>()
        mockkObject(RetrofitFactory)

        every { RetrofitFactory.getInstance(context) } returns retrofit
        every { retrofit.create(CurrencyApi::class.java) } returns currencyApi
        val result = CurrencyCloud.getCurrencyAPI(context)
        assertEquals(currencyApi, result)
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
