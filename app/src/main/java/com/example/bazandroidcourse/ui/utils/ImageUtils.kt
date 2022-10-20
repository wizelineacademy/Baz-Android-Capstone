package com.example.bazandroidcourse.ui.utils

import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel

fun createURLImage( ticker:String, size:Int = 32):String {
    return "https://cryptoflash-icons-api.herokuapp.com/$size/${ticker.substring(0,ticker.indexOf("_"))}"
}

fun BookModel.getIcon():String {
    return createURLImage(book)
}

fun BookDetailModel.getIcon():String {
    return createURLImage(book)
}