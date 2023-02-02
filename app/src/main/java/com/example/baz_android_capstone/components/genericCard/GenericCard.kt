package com.example.baz_android_capstone.components.genericCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GenericCard(
    genericCardInterface: GenericCardInterface,
    modifier: Modifier = Modifier
) {
    val borderWidth = with(LocalDensity.current) { 8.dp.toPx() }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (genericCardInterface is GenericCardPresentation) {
                    genericCardInterface.onClick.invoke()
                }
            },
        color = genericCardInterface.background,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    (
                        if (genericCardInterface is GenericCardDescription) {
                            genericCardInterface.border
                        } else Color.Transparent
                        )?.let {
                        drawLine(
                            color = it,
                            start = Offset(x = 0f, y = size.height),
                            end = Offset(x = 0f, y = 0f),
                            strokeWidth = borderWidth
                        )
                    }
                }
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(shape = CircleShape)
                    .size(40.dp)
                    .background(Color.White),
                imageModel = genericCardInterface.glideModel.url,
                contentScale = genericCardInterface.glideModel.contentScale,
                placeHolder = painterResource(id = genericCardInterface.glideModel.placeholder),
                error = painterResource(id = genericCardInterface.glideModel.placeholder),
                previewPlaceholder = genericCardInterface.glideModel.placeholder,
                requestOptions =
                {
                    if (genericCardInterface.glideModel.isRoundedShape) {
                        RequestOptions.circleCropTransform()
                    } else {
                        RequestOptions.noTransformation()
                    }
                }
            )
            genericCardInterface.title?.let { Text(text = it) }
        }
    }
}
