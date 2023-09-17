package com.example.currencyapp.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.currencyapp.ui.screen.CurrencyScreen
import com.example.currencyapp.ui.screen.DetailsScreen
import com.example.currencyapp.ui.viewmodel.CurrencyViewModel
import com.example.currencyapp.ui.viewmodel.DetailsViewModel

@Composable
fun CurrencyNavigationController(
    viewModel: CurrencyViewModel,
    detailsViewModel: DetailsViewModel,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Currency.route) {
        composable(Screens.Currency.route) {
            CurrencyScreen(
                viewModel,
            ) {
                navController.navigate(Screens.Details.route)
            }
        }
        composable(Screens.Details.route) {
            DetailsScreen(detailsViewModel)
        }
    }
}

sealed class Screens(val route: String) {
    object Currency : Screens("currency")
    object Details : Screens("details")
}
