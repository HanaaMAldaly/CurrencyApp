package com.example.currencyapp.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType

@ExperimentalMaterial3Api
@Composable
fun CurrencyTextField(value: Float, onValueChanged: (String) -> Unit) {
    TextField(
        value = "$value",
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
    )
}
