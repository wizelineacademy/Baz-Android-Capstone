package com.example.bazandroidcourse.data.model

import com.example.bazandroidcourse.ui.utils.createURLImageByBookId

open class Book( open val book:String = "" ){
    fun getUrlIcon():String {
        return createURLImageByBookId(book)
    }
}