package com.example.bazandroidcourse.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.bazandroidcourse.R

@Composable
fun OperationItem( price:String = "30000",amount:String = "88888888" ) {
    val priceLabel = stringResource(id = R.string.detail_price)
    val amountLabel = stringResource(id = R.string.detail_amount)
    Surface(modifier = Modifier) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colors.primaryVariant)){
                    append("$priceLabel\n")
                }
                withStyle(style = SpanStyle()){
                    append("$price\n")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colors.primaryVariant)){
                    append("$amountLabel\n")
                }
                withStyle(style = SpanStyle()){
                    append("$amount\n")
                }
            }
        )
    }
}