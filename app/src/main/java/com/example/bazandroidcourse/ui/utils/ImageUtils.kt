package com.example.bazandroidcourse.ui.utils

import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel

fun createURLImage( ticker:String, size:Int = 200):String {
    return "https://cryptoicons.org/api/icon/${ticker.substring(2)}/$size"
}

fun BookModel.getIcon():String {
    return createURLImage(book)
}

fun BookDetailModel.getIcon():String {
    return createURLImage(book)
}