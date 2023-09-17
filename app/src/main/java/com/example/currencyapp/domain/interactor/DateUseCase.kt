package com.example.currencyapp.domain.interactor

import java.text.SimpleDateFormat
import java.util.Calendar

class DateUseCase : (Int) -> String {
    override fun invoke(day: Int): String {
        val calender = Calendar.getInstance()
        calender.add(Calendar.DATE, day)
        return SimpleDateFormat("yyyy-MM-dd")
            .format(calender.time)
    }
}
