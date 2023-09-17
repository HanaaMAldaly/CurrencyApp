package com.example.currencyapp.data.remote.network

import android.content.Context
import com.example.currencyapp.data.remote.exception.NetworkConnectionInterceptor
import com.example.currencyapp.data.remote.response.LatestCurrencyRatesResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    // TODO make this keys secure
    private const val BASE_URL = "http://data.fixer.io/api/"
    private const val ACCESS_KEY = "access_key"
    private const val ACCESS_KEY_VALUE = "b0b396b1b9002aa4a9a256a43b5bba88"
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LatestCurrencyRatesResponse::class.java, CurrencyRatesDeserializer())
        .create()

    fun getInstance(context: Context): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(NetworkConnectionInterceptor(context))
                .addInterceptor {
                    val r = it.request()
                    val url = r.url().newBuilder()
                        .addQueryParameter(ACCESS_KEY, ACCESS_KEY_VALUE)
                        .build()
                    val request = r.newBuilder()
                        .url(url)
                        .build()
                    it.proceed(request)
                }
                .build(),
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
