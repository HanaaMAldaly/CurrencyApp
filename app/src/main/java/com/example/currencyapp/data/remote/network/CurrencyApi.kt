package com.example.currencyapp.data.remote.network

import com.example.currencyapp.data.remote.response.ConvertCurrencyResponse
import com.example.currencyapp.data.remote.response.LatestCurrencyRatesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest")
    suspend fun getLatestCurrencyRates():
        LatestCurrencyRatesResponse

    @GET("convert")
    suspend fun convertCurrency(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Float,
        @Query("date") date: String,
    ): ConvertCurrencyResponse

    @GET("{date}")
    suspend fun getHistory(@Path("date") date: String):
        LatestCurrencyRatesResponse
}
