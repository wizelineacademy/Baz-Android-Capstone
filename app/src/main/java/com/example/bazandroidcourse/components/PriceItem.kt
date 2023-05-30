package com.example.bazandroidcourse.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, widthDp = 500)
@Composable
fun PriceItem(
    title:String = "title",
    price:String = "3000",
    currency:String = "BTC",
    color:Color = Color.Green,
    backgroundColor:Color = MaterialTheme.colors.secondary
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        shape = RoundedCornerShape(20.dp),
        color = backgroundColor

    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding( 10.dp)) {
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .fillMaxWidth(),
                text = title
            )
            Text(
               modifier = Modifier.fillMaxWidth(),
               text =  buildAnnotatedString {
                    withStyle(
                        style = ParagraphStyle( TextAlign.Right),
                    ){
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                color = color
                            )
                        ) {
                            append(price)
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.LightGray
                            )
                        ) {
                            append(" ")
                            append(currency)
                        }
                    }

                }
            )
        }
    }
}