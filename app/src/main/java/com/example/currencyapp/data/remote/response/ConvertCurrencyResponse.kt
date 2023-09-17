package com.example.currencyapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ConvertCurrencyResponse(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("amount") val amount: Float?,
)
