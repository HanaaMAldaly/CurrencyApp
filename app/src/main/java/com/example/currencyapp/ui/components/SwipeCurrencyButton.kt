package com.example.currencyapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SwipeCurrencyButton(
    onWipe: () -> Unit,
) {
    Box(
        modifier = Modifier.clickable {
            onWipe.invoke()
        },
    ) {
        Icon(Icons.Default.ArrowForward, contentDescription = "")
    }
}
