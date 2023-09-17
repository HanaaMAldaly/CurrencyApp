package com.example.currencyapp.data.remote.network

import android.content.Context
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitFactoryTest {
    @Test
    fun testRetrofitBuilder() {
        val retrofit = mockk<Retrofit>()
        val context = mockk<Context>()
        mockkConstructor(Retrofit.Builder::class)
        every { anyConstructed<Retrofit.Builder>().build() } returns retrofit

        assertEquals(retrofit, RetrofitFactory.getInstance(context))
        verify {
            anyConstructed<Retrofit.Builder>().baseUrl(any<String>())
            anyConstructed<Retrofit.Builder>().client(any())
            anyConstructed<Retrofit.Builder>().addConverterFactory(any<GsonConverterFactory>())
        }
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
