package com.carteagal.baz_android.utils

import com.carteagal.baz_android.utils.Constants.IMAGE_SIZE
import com.carteagal.baz_android.utils.Constants.PATH_ICON

object BaseUrlImage {
    fun generateUrlImage(book: String, style: String = "") =
        Constants.BASE_IMAGE_URL + PATH_ICON + book.split("_")[0] + "/" + IMAGE_SIZE
}