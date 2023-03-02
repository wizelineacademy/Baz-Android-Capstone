package com.example.baz_android_capstone.components.dropdownMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.baz_android_capstone.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutlinedTextFieldMenu(
    valor: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean,
    icon: ImageVector,
    expand: MutableState<Boolean>,
    label: String
) {
    val interactionSource = remember { MutableInteractionSource() }

    val colors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = PlateColor,
        focusedBorderColor = GoldColor,
        backgroundColor = Color.White,
        focusedLabelColor = GoldColor,
        unfocusedLabelColor = BronzeColor,
        textColor = Color.Gray
    )

    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        value = valor,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = colors.backgroundColor(enabled = true).value,
                shape = RoundedCornerShape(corner = CornerSize(cornerSize))
            )
            .fillMaxWidth()
            .onFocusChanged {
                expand.value = it.isFocused
            }
            .focusRequester(focusRequester),
        interactionSource = interactionSource,
        readOnly = readOnly,
        enabled = true,
        singleLine = true,
        textStyle = TextStyle(
            color = colors.textColor(enabled = true).value,
            fontSize = 14.sp
        )
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = valor,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            label = {
                Text(
                    text = label,
                    modifier = Modifier.padding(horizontal = spacer8)
                )
            },
            singleLine = true,
            enabled = true,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "trailing icon",
                    modifier = Modifier.clickable {
                        expand.value = !expand.value
                        if (expand.value) focusRequester.requestFocus()
                    }
                )
            },
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = spacer16,
                end = spacer8,
                top = empty,
                bottom = empty
            ),
            border = {
                TextFieldDefaults.BorderBox(
                    enabled = true,
                    isError = false,
                    colors = colors,
                    interactionSource = interactionSource,
                    shape = RoundedCornerShape(corner = CornerSize(cornerSize)),
                    unfocusedBorderThickness = borderStroke2,
                    focusedBorderThickness = borderStroke2
                )
            },
            colors = colors
        )
    }
}
