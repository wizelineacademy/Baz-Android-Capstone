package com.example.bazandroidcourse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bazandroidcourse.R
import com.example.bazandroidcourse.data.model.staticdata.ApplicationCurrency

@Preview(showBackground = true)
@Composable
fun CurrencyDropDownMenu(
    currentCurrency:ApplicationCurrency = ApplicationCurrency.Bitcoin,
    items : List<ApplicationCurrency> = emptyList(),
    onValueChange: (ApplicationCurrency)->Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var expanded by remember {
            mutableStateOf(false)
        }
        Text(
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize =  14.sp
            ),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                ,
            text = stringResource(id = R.string.home_currency)
        )
        Text(text = currentCurrency.name)
        IconButton(onClick = {
            expanded = !expanded
        }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "More"
            )
        }
        DropdownMenu(
            modifier = Modifier
                .padding(start = 20.dp)
                ,
            expanded = expanded, onDismissRequest = { expanded = false}) {
            items.forEach {
                Text(text = it.name, modifier= Modifier
                    .clickable {
                        expanded = !expanded
                        onValueChange(it)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp) )
            }
        }
    }
}
