package com.andcourse.domain.model

import com.andcourse.domain.utils.createURLImageByBookId

open class Book( open val book:String = "" ){
    fun getUrlIcon():String {
        return createURLImageByBookId(book)
    }
}