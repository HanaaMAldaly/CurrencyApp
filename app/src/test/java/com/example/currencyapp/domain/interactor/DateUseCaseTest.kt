package com.example.currencyapp.domain.interactor

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

internal class DateUseCaseTest {
    @Test
    fun testDateUseCase_whenPass0Days_expectReturnTodayDate() {
        val expectedResult = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val useCase = DateUseCase()
        val result = useCase.invoke(0)
        assertEquals(expectedResult, result)
    }

    @Test
    fun testDateUseCase_whenPassMinus1Days_expectReturnYesterdayDate() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val expectedResult = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
        val useCase = DateUseCase()
        val result = useCase.invoke(-1)
        assertEquals(expectedResult, result)
    }
}
