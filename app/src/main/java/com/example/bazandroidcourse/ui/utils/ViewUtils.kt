package com.example.bazandroidcourse.ui.utils

import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel

fun createURLImage( ticker:String, size:Int = 32):String {
    return "https://cryptoflash-icons-api.herokuapp.com/$size/${getTicker(ticker)}"
}


fun getTicker( bookId:String):String {
   return  bookId.substring(0,bookId.indexOf("_"))
}

fun getUnit(bookId: String):String{
    return bookId.substring(bookId.indexOf("_")+1)
}

fun BookModel.getIcon():String {
    return createURLImage(book)
}

fun BookDetailModel.getIcon():String {
    return createURLImage(book)
}