package com.example.bazandroidcourse.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.andcourse.domain.model.BookModel

@Preview(showBackground = true, widthDp = 400)
@Composable
fun BookItem(element: BookModel = BookModel(), onClick:(BookModel)->Unit ={}) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        shape= RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, color = Color(com.example.bazandroidcourse.R.color.background)),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background( MaterialTheme.colors.onSecondary)
                .clickable { onClick(element) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp),
                painter = rememberImagePainter(data = element.getUrlIcon()) {
                    crossfade(true)
                },
                contentDescription = "icon",
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                style = MaterialTheme.typography.body2,
                text = element.name,
                textAlign = TextAlign.Center
            )
        }
    }

}