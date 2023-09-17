package com.example.currencyapp.data.remote.network

import com.example.currencyapp.data.remote.response.ErrorResponse
import com.example.currencyapp.data.remote.response.LatestCurrencyRatesResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Test
import java.io.IOException

internal class CurrencyRatesDeserializerTest {
    @Test
    fun test_CurrencyRatesDeserializer_whenNoErrors_expectDeserializeObject() {
        val expectedResult = LatestCurrencyRatesResponse(true, null, "base", listOf())
        val deserializer = CurrencyRatesDeserializer()

        val jsonElement = mockk<JsonElement>()
        val jsonObject = mockk<JsonObject>(relaxed = true)
        val context = mockk<JsonDeserializationContext>()

        every { jsonElement.asJsonObject } returns jsonObject

        every {
            context.deserialize<Boolean?>(
                jsonObject.get("success"),
                Boolean::class.java,
            )
        } returns true
        every {
            context.deserialize<String?>(
                jsonObject.get("base"),
                String::class.java,
            )
        } returns "base"
        every { jsonObject.get("rates").asJsonObject.entrySet() } returns setOf()
        val result = deserializer.deserialize(jsonElement, mockk(), context)

        assertEquals(expectedResult, result)
    }

    @Test(expected = IOException::class)
    fun test_CurrencyRatesDeserializer_whenJsonIsNull_expectThrowException() {
        val deserializer = CurrencyRatesDeserializer()
        val context = mockk<JsonDeserializationContext>()

        deserializer.deserialize(null, mockk(), context)
    }

    @Test(expected = IOException::class)
    fun test_CurrencyRatesDeserializer_whenContextIsNull_expectThrowException() {
        val deserializer = CurrencyRatesDeserializer()

        deserializer.deserialize(mockk(), mockk(), null)
    }

    @Test(expected = IOException::class)
    fun test_CurrencyRatesDeserializer_whenErrors_expectDeserializeObject() {
        val deserializer = CurrencyRatesDeserializer()

        val jsonElement = mockk<JsonElement>()
        val jsonObject = mockk<JsonObject>(relaxed = true)
        val context = mockk<JsonDeserializationContext>()

        every { jsonElement.asJsonObject } returns jsonObject

        every {
            context.deserialize<Boolean?>(
                jsonObject.get("success"),
                Boolean::class.java,
            )
        } returns false
        every {
            context.deserialize<ErrorResponse?>(
                jsonObject.get("error"),
                Boolean::class.java,
            )
        } returns ErrorResponse(12, "")

        deserializer.deserialize(jsonElement, mockk(), context)
    }

    @After
    fun clearMocks() {
        clearAllMocks()
    }
}
