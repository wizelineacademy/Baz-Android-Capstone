package com.example.baz_android_capstone.components.glideOptimized

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.example.baz_android_capstone.util.IMAGE_SIZE
import com.example.baz_android_capstone.util.SingleInstance
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun GlideOptimized(
    modifier: Modifier,
    glideModel: GlideModel
) {
    val imageSize = remember { mutableStateOf(IMAGE_SIZE) }
    val resource = remember { mutableStateOf("") }
    if (SingleInstance.urlList[glideModel.url] == null) {
        LaunchedEffect(key1 = glideModel.url) {
            withContext(Dispatchers.IO) {
                var urlConnection: HttpURLConnection? = null
                try {
                    val url = URL(glideModel.url)
                    urlConnection = url.openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.inputStream.close()
                    imageSize.value = urlConnection.contentLength
                    SingleInstance.urlList[glideModel.url] = urlConnection.contentLength
                    resource.value = glideModel.url
                } catch (exception: Exception) {
                    exception.printStackTrace()
                } finally {
                    urlConnection?.disconnect()
                }
            }
        }
    } else {
        if (SingleInstance.urlList[glideModel.url]!! in 1 until IMAGE_SIZE) {
            resource.value = glideModel.url
        } else {
            resource.value = ""
        }
    }

    GlideImage(
        modifier = modifier,
        imageModel = resource.value,
        contentScale = glideModel.contentScale,
        placeHolder = painterResource(id = glideModel.placeholder),
        error = painterResource(id = glideModel.placeholder),
        previewPlaceholder = glideModel.placeholder,
        requestOptions =
        {
            if (glideModel.isRoundedShape) {
                RequestOptions.circleCropTransform()
            } else {
                RequestOptions.noTransformation()
            }
        }

    )
}

@Composable
@Preview(showBackground = true, name = "Circle Shape Preview")
fun GlideImageOptimizedCircleShapePreview() {
    val glideModel = GlideModel(
        url = "",
        isRoundedShape = true,
        contentScale = ContentScale.FillWidth
    )

    Column() {
        GlideOptimized(
            modifier = Modifier
                .padding(16.dp)
                .clip(shape = CircleShape)
                .size(100.dp)
                .background(Color.LightGray),
            glideModel = glideModel
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Rounded Corners Preview")
fun GlideImageOptimizedRoundedCornerPreview() {
    val glideModel = GlideModel(
        url = "",
        contentScale = ContentScale.FillWidth
    )

    Column() {
        GlideOptimized(
            modifier = Modifier
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(size = 14.dp))
                .size(100.dp)
                .background(Color.LightGray),
            glideModel = glideModel
        )
    }
}
