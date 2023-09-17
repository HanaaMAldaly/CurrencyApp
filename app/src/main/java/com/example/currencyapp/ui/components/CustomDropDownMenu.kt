package com.example.currencyapp.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencyapp.domain.model.Currency

@Composable
fun CurrencyDropDownMenuComponent(
    items: List<Currency>,
    text: String,
    onItemsSelected: (String) -> Unit,
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    Log.i("TAG", "${items.size}")
    Column {
        Row(
            modifier = Modifier.clickable {
                isExpanded.value = !isExpanded.value
            },
        ) {
            Text(text = text, style = MaterialTheme.typography.bodyLarge)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
        }
        CurrencyDropDownMenu(items = items, isExpanded, onItemsSelected)
    }
}

@Composable
fun CurrencyDropDownMenu(
    items: List<Currency>,
    isExpanded: MutableState<Boolean>,
    onItemsSelected: (String) -> Unit,
) {
    Log.i("TAG", "${items.size}")
    DropdownMenu(
        expanded = isExpanded.value,
        onDismissRequest = { isExpanded.value = false },
        modifier = Modifier.width(200.dp)
            .height(100.dp),
    ) {
        items.forEach {
            DropdownMenuItem(
                text = { Text(text = it.code ?: "") },
                onClick = {
                    onItemsSelected.invoke(it.code ?: "")
                    isExpanded.value = false
                },
            )
        }
    }
}
