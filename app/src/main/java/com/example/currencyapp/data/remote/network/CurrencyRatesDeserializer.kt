package com.example.currencyapp.data.remote.network

import com.example.currencyapp.data.remote.response.ErrorResponse
import com.example.currencyapp.data.remote.response.LatestCurrencyRatesResponse
import com.example.currencyapp.data.remote.response.RatesResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.io.IOException
import java.lang.reflect.Type

class CurrencyRatesDeserializer : JsonDeserializer<LatestCurrencyRatesResponse> {
    override fun deserialize(
        json: JsonElement?,
        type: Type?,
        context: JsonDeserializationContext?,
    ): LatestCurrencyRatesResponse {
        if (json == null || context == null) {
            throw IOException("Parse Error")
        }
        val obj = json.asJsonObject

        val success = context.deserialize<Boolean?>(obj.get("success"), Boolean::class.java)
        if (!success) {
            val error = context.deserialize<ErrorResponse?>(obj.get("error"), Boolean::class.java)

            throw IOException(error.info)
        }
        val base = context.deserialize<String?>(obj.get("base"), String::class.java)

        val ratesSet = obj.get("rates").asJsonObject.entrySet()
        val ratesList = ratesSet.map {
            val code = it.key
            val value = it.value.asFloat
            RatesResponse(code, value)
        }

        return LatestCurrencyRatesResponse(success, null, base, ratesList)
    }
}
