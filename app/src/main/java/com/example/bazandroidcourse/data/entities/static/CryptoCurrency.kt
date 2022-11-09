package com.example.bazandroidcourse.data.entities.static

import com.example.bazandroidcourse.ui.utils.createURLImage

abstract class CryptoCurrency(
    val id: String,
    val name: String,
    val trading :Boolean = false
) {
    fun getUrlIcon(): String = createURLImage(id)
    fun itsMe(ticker: String): Boolean = ticker.startsWith("${id}_")
    fun itsMyName(name: String): Boolean = this.name.equals(name)
}