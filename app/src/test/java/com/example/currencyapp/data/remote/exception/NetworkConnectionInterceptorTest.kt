package com.example.currencyapp.data.remote.exception

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import okhttp3.Interceptor
import okhttp3.Request
import org.junit.After
import org.junit.Test
import java.io.IOException

internal class NetworkConnectionInterceptorTest {
    @Test
    fun testNetworkConnectionInterceptor_whenIsConnected_expectProceedTheRequest() {
        val context = mockk<Context>()
        val networkInfo = mockk<NetworkInfo>()
        val connectivityManager = mockk<ConnectivityManager>()
        val chain = mockk<Interceptor.Chain>()
        val request = mockk<Request>()
        val builder = mockk<Request.Builder>()

        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
        every { (connectivityManager).activeNetworkInfo } returns networkInfo
        every { networkInfo.isConnected } returns true
        every { request.newBuilder() } returns builder
        every { builder.build() } returns request
        every { chain.proceed(request) } returns mockk()
        every { chain.request() } returns request
        val interceptor = NetworkConnectionInterceptor(context)

        interceptor.intercept(chain)

        verify {
            chain.proceed(request)
        }
    }

    @Test(expected = IOException::class)
    fun testNetworkConnectionInterceptor_whenIsNotConnected_expectThrowIOException() {
        val context = mockk<Context>()
        val networkInfo = mockk<NetworkInfo>()
        val connectivityManager = mockk<ConnectivityManager>()
        val chain = mockk<Interceptor.Chain>()

        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
        every { (connectivityManager).activeNetworkInfo } returns networkInfo
        every { networkInfo.isConnected } returns false
        val interceptor = NetworkConnectionInterceptor(context)

        interceptor.intercept(chain)
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
