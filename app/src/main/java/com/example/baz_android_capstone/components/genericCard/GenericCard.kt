package com.example.baz_android_capstone.components.genericCard // ktlint-disable package-name

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.example.baz_android_capstone.util.*
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GenericCard(genericCardInterface: GenericCardInterface) {
    val borderWidth = with(LocalDensity.current) { spacer16.toPx() }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (genericCardInterface is GenericCardPresentation) {
                    genericCardInterface.onClick.invoke()
                }
            },
        color = genericCardInterface.background,
        border = if (genericCardInterface is GenericCardPresentation) {
            BorderStroke(width = borderStroke4, color = GoldColor)
        } else {
            null
        },
        shape = RoundedCornerShape(corner = CornerSize(cornerSize)),
        elevation = elevation
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
                .padding(vertical = spacer16, horizontal = spacer32),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*GlideOptimized(
                modifier = Modifier
                    .spinning()
                    .padding(16.dp)
                    .clip(shape = CircleShape)
                    .size(40.dp)
                    .background(Color.LightGray),
                glideModel = genericCardInterface.glideModel
            )*/
            if (genericCardInterface is GenericCardPresentation) {
                GlideImage(
                    modifier = Modifier
                        .spinning()
                        .clip(shape = CircleShape)
                        .size(spacer40)
                        .background(Color.LightGray),
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
                Spacer(modifier = Modifier.width(24.dp))
            }
            genericCardInterface.title?.let {
                Text(
                    modifier = Modifier.padding(start = if (genericCardInterface is GenericCardDescription) spacer16 else empty),
                    text = it,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = BronzeColor
                )
            }
        }
    }
}
