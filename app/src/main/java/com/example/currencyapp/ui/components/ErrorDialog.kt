package com.example.currencyapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.currencyapp.R

@Composable
fun ErrorDialog(title: String, showDialog: Boolean, retry: () -> Unit) {
    if (showDialog) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Column() {
                Text(title)
                Button(onClick = {
                    retry.invoke()
                }) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        }
    }
}
