package com.example.baz_android_capstone.components.radioButton

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.baz_android_capstone.util.GoldColor
import com.example.baz_android_capstone.util.PlateColor
import com.example.baz_android_capstone.util.spacer16
import com.example.baz_android_capstone.util.spacer24

@Composable
fun RadioButtonGroup(
    options: List<String>,
    onChange: (String) -> Unit = {}
) {
    val radioButtonColors = RadioButtonDefaults.colors(
        selectedColor = GoldColor,
        unselectedColor = PlateColor
    )

    val selected = remember { mutableStateOf(-1) }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEachIndexed { i, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.size(spacer24),
                    selected = selected.value == i,
                    onClick = {
                        selected.value = i
                        onChange(option)
                    },
                    colors = radioButtonColors
                )
                Spacer(modifier = Modifier.width(spacer16))
                Text(
                    text = option,
                    style = MaterialTheme.typography.body1,
                    color = GoldColor
                )
            }
        }
    }
}
