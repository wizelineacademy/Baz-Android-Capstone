package com.example.bazandroidcourse.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.bazandroidcourse.R
import com.example.bazandroidcourse.data.model.BookDetailModel
import com.example.bazandroidcourse.ui.utils.getTickerFor

@Preview(showBackground = true, widthDp = 300)
@Composable
private fun NavigationBar(
    leftIcon:@Composable () -> Unit = {},
    title:@Composable () -> Unit = {},
    rightIcon: @Composable () -> Unit  = {}
) {
    Surface(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            leftIcon()
            title()
            rightIcon()
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun DetailNavigationBar(  title:String= "tilte", model:BookDetailModel = BookDetailModel(), isDetailNavBar:Boolean = true, onBackButton:()->Unit = {}) {
    NavigationBar(
        leftIcon = {
            Icon(
                modifier = Modifier.size(30.dp).clickable { onBackButton() },
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = "backButton"
            )
        },
        title = {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Title(title)
                if (isDetailNavBar) {
                    Text(text = model.book.getTickerFor() )
                    Text(text = model.volume )
                }
            }
        },
        rightIcon = {
            if(isDetailNavBar){
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = rememberImagePainter(
                        data = model.getUrlIcon()
                    ),
                    contentDescription = ""
                )
            }
        }
    )
}
