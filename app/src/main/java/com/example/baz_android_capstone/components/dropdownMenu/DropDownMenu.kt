package com.example.baz_android_capstone.components.dropdownMenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownTextMenu(
    listMarkets: List<String>,
    label: String,
    selectedMarket: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    val selectedItem = remember { mutableStateOf("") }

    val textFieldSize = remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    val focusManager = LocalFocusManager.current

    Column() {
        OutlinedTextFieldMenu(
            modifier = Modifier.onGloballyPositioned {
                textFieldSize.value = it.size.toSize()
            },
            valor = selectedItem.value,
            onValueChange = { selectedItem.value = it },
            readOnly = true,
            icon = icon,
            expand = expanded,
            label = label
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
                focusManager.clearFocus()
            },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.value.width.toDp() })
        ) {
            listMarkets.forEach {
                DropdownMenuItem(onClick = {
                    selectedMarket(it)
                    selectedItem.value = it
                    expanded.value = false
                    focusManager.clearFocus()
                }) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
