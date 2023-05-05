package com.example.bazandroidcourse.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.ui.utils.getIcon

@Preview(showBackground = true)
@Composable
fun BookItem(element: BookModel = BookModel()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .padding(5.dp),
            painter = rememberImagePainter(data = element.getIcon()){
                crossfade(true)
            },
            contentDescription = "icon",
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text( style = MaterialTheme.typography.body2, text = element.name, textAlign = TextAlign.Center)
    }

}