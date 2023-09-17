package com.example.currencyapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencyapp.domain.model.CurrencyRate

@Composable
fun CurrencyList(title: String, items: List<CurrencyRate>) {
    LazyColumn(
        state = rememberLazyListState(),
    ) {
        item {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp),
            )
        }
        items(items) {
            Text(
                text = "${it.code}->${it.value}",
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}
