package com.example.currencyapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.currencyapp.R
import com.example.currencyapp.ui.components.CurrencyDropDownMenuComponent
import com.example.currencyapp.ui.components.CurrencyTextField
import com.example.currencyapp.ui.components.ErrorDialog
import com.example.currencyapp.ui.components.SwipeCurrencyButton
import com.example.currencyapp.ui.viewmodel.CurrencyViewModel

@Composable
fun CurrencyScreen(
    viewModel: CurrencyViewModel,
    onDetails: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ConvertCurrencyView(
            viewModel,
        )
        Button(onClick = {
            onDetails.invoke()
        }) {
            Text(text = stringResource(id = R.string.details))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertCurrencyView(
    viewModel: CurrencyViewModel,
) {
    val result = viewModel.currencyObservable.collectAsState().value
    val selectedItemFrom by viewModel.fromCurrency.collectAsState()
    val selectedItemTo by viewModel.toCurrency.collectAsState()
    val convertValueResult = viewModel.amount.collectAsState().value
    val convertCurrencyErrorDialog = viewModel.convertCurrencyErrorDialog.collectAsState()

    ErrorDialog(
        title = convertValueResult.exceptionOrNull()?.localizedMessage
            ?: "",
        convertCurrencyErrorDialog.value ?: false,
        viewModel::convertCurrency,
    )
    val listCurrencyErrorDialog = viewModel.listCurrencyErrorDialog.collectAsState()
    val msg = result?.exceptionOrNull()?.localizedMessage
    if (msg != null) {
        ErrorDialog(
            msg,
            listCurrencyErrorDialog.value ?: false,
            viewModel::getCurrencyList,
        )
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.padding(16.dp)) {
            CurrencyDropDownMenuComponent(
                result?.getOrNull() ?: listOf(),
                selectedItemFrom ?: stringResource(id = R.string.from),
            ) { viewModel.fromCurrency.value = it }
            SwipeCurrencyButton(viewModel::onSwipe)
            CurrencyDropDownMenuComponent(
                result?.getOrNull() ?: listOf(),
                selectedItemTo ?: stringResource(id = R.string.to),
            ) { viewModel.toCurrency.value = it }
        }
        val fromValue by viewModel.currencyAmount.collectAsState()
        CurrencyTextField(fromValue) { viewModel.currencyAmount.value = it.toFloat() }
        if (fromValue != 0f && selectedItemFrom != null && selectedItemTo != null) {
            viewModel.convertCurrency()
        }
    }
    Text(
        text = convertValueResult.getOrDefault(0f).toString(),
        modifier = Modifier.padding(16.dp),
    )
}
