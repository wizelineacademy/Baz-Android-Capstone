package com.example.bazandroidcourse.ui.utils

import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.static.ApplicationCurrencies


fun getTicker( bookId:String):String {
   return  bookId.substring(0,bookId.indexOf("_"))
}

fun getCurrency(bookId: String):String{
    return bookId.substring(bookId.indexOf("_")+1)
}

fun createURLImage( tickerId: String, size:Int = 32):String {
    return createURLImageById(getTicker(tickerId), size)
}

fun createURLImageById( tickerId:String, size:Int = 32):String {
    // todo:send this to build field
    return "https://cryptoflash-icons-api.herokuapp.com/$size/${tickerId}"
}

fun BookModel.getIcon():String {
    return createURLImage(book)
}

fun BookDetailModel.getIcon():String {
    return createURLImage(book)
}

fun String.cryptoName():String{
    return ApplicationCurrencies.findByTicker(this)?.name ?: this
}