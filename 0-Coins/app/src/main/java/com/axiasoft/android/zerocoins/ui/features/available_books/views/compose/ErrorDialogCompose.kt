package com.axiasoft.android.zerocoins.ui.features.available_books.views.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ErrorDataFetchAlertDialog(okText: String, title: String) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { showDialog = true }) {
            Text(text = okText)
//
        }

        if (showDialog) {
            AlertDialog(
                title = { Text(text = "Continuar") },
                text = { Text(text = "Continuar") },
                onDismissRequest = { showDialog = false },
                buttons = {}
            )
        }
//
    }
}
