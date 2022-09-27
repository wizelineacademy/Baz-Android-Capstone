package com.example.readbitso.composeViews

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.readbitso.R


@Composable
fun Loading(message:String="" )
{
    Surface {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.criptoinfo),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth())
            }
        )

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
            Text(message, modifier = Modifier.padding(4.dp))
        }
    }
}