package com.example.bazandroidcourse.data.entities.static

import com.example.bazandroidcourse.ui.utils.createURLImageByBookId

abstract class CryptoCurrency(
    val id: String,
    val name: String,
    val trading: Boolean = false
) {
    fun getUrlIcon(): String = createURLImageByBookId(id)
}

