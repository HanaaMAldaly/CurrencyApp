package com.example.currencyapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LatestCurrencyRatesResponse(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("base") val base: String,
    @SerializedName("rates") val rates: List<RatesResponse>,
)
