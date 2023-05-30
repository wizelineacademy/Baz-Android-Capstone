package com.example.bazandroidcourse.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Subtitle(text:String){
    Text(
        modifier = Modifier.padding(vertical = 5.dp),
        text = text,
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center
    )
}