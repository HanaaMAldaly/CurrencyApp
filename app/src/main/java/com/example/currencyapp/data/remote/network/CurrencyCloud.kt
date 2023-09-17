package com.example.currencyapp.data.remote.network

import android.content.Context

object CurrencyCloud {
    fun getCurrencyAPI(context: Context): CurrencyApi =
        RetrofitFactory.getInstance(context).create(CurrencyApi::class.java)
}
