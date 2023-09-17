package com.example.currencyapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.currencyapp.data.repository.CurrencyRepositoryImpl
import com.example.currencyapp.data.repository.HistoryRepositoryImpl
import com.example.currencyapp.domain.interactor.ConvertCurrencyUseCase
import com.example.currencyapp.domain.interactor.CurrencyHistoryUseCase
import com.example.currencyapp.domain.interactor.CurrencyListUseCase
import com.example.currencyapp.domain.interactor.CurrencyRateUseCase
import com.example.currencyapp.ui.components.CurrencyNavigationController
import com.example.currencyapp.ui.theme.CurrencyAppTheme
import com.example.currencyapp.ui.viewmodel.CurrencyViewModel
import com.example.currencyapp.ui.viewmodel.DetailsViewModel
import com.example.currencyapp.ui.viewmodel.factory.CurrencyViewModelFactory
import com.example.currencyapp.ui.viewmodel.factory.DetailsViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<CurrencyViewModel> {
        val repo = CurrencyRepositoryImpl(this)
        CurrencyViewModelFactory(CurrencyListUseCase(repo), ConvertCurrencyUseCase(repo))
    }
    private val detailsViewModel by viewModels<DetailsViewModel> {
        val repo = HistoryRepositoryImpl(this)
        DetailsViewModelFactory(CurrencyHistoryUseCase(repo), CurrencyRateUseCase(repo))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CurrencyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    CurrencyNavigationController(viewModel, detailsViewModel)
                }
            }
        }
    }
}
