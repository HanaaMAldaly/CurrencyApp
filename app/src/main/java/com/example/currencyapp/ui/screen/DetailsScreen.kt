package com.example.currencyapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.currencyapp.R
import com.example.currencyapp.ui.components.CurrencyList
import com.example.currencyapp.ui.components.ErrorDialog
import com.example.currencyapp.ui.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        val historyResult = detailsViewModel.history.collectAsState().value
        val msg = historyResult?.exceptionOrNull()?.localizedMessage
        if (msg != null) {
            ErrorDialog(
                title = msg,
                detailsViewModel.historyErrorDialog.collectAsState().value ?: false,
                detailsViewModel::getHistory,
            )
        }
        if (historyResult?.isSuccess == true) {
            CurrencyList(
                stringResource(id = R.string.history),
                historyResult?.getOrDefault(listOf()) ?: listOf(),
            )
        }
        val rateResult = detailsViewModel.history.collectAsState().value
        if (rateResult?.isSuccess == true) {
            CurrencyList(
                stringResource(id = R.string.recent_rates),
                rateResult?.getOrDefault(listOf()) ?: listOf(),
            )
        }

        val rateMsg = rateResult?.exceptionOrNull()?.localizedMessage
        if (rateMsg != null) {
            ErrorDialog(
                title = rateMsg,
                detailsViewModel.rateErrorDialog.collectAsState().value ?: false,
                detailsViewModel::getRateCurrency,
            )
        }
    }
}
