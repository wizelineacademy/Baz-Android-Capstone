package com.example.baz_android_capstone.components.genericCard

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.baz_android_capstone.util.spacer24

@Composable
fun GenericCardList(
    modifier: Modifier = Modifier,
    elements: List<GenericCardInterface>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(elements) { element ->
            GenericCard(genericCardInterface = element)
            Spacer(modifier = Modifier.height(spacer24))
        }
    }
}