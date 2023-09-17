package com.example.currencyapp.ui.viewmodel.factory

import com.example.currencyapp.domain.interactor.CurrencyHistoryUseCase
import com.example.currencyapp.domain.interactor.CurrencyRateUseCase
import com.example.currencyapp.ui.viewmodel.DetailsViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import org.junit.Test

internal class DetailsViewModelFactoryTest {
    @Test
    fun testFactoryCreate() {
        val historyUseCase = mockk<CurrencyHistoryUseCase>()
        val ratesUseCase = mockk<CurrencyRateUseCase>()
        val factory = DetailsViewModelFactory(historyUseCase, ratesUseCase)

        assertNotNull(factory.create(DetailsViewModel::class.java))
    }
}
