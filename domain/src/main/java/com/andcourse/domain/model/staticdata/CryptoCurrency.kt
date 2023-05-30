package com.andcourse.domain.model.staticdata

import com.andcourse.domain.utils.createURLImageByBookId


abstract class CryptoCurrency(
    val id: String,
    val name: String,
    val trading: Boolean = false
) {
    fun getUrlIcon(): String = createURLImageByBookId(id)
}

