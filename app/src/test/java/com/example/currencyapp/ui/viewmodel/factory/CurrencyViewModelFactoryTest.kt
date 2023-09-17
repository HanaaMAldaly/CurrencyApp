package com.example.currencyapp.ui.viewmodel.factory

import com.example.currencyapp.domain.interactor.ConvertCurrencyUseCase
import com.example.currencyapp.domain.interactor.CurrencyListUseCase
import com.example.currencyapp.ui.viewmodel.CurrencyViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import org.junit.Test

internal class CurrencyViewModelFactoryTest {
    @Test
    fun testFactoryCreate() {
        val currencyListUseCase = mockk<CurrencyListUseCase>()
        val convertCurrencyUseCase = mockk<ConvertCurrencyUseCase>()
        val factory = CurrencyViewModelFactory(currencyListUseCase, convertCurrencyUseCase)

        assertNotNull(factory.create(CurrencyViewModel::class.java))
    }
}
