package com.jpgl.cryptocurrencies.utils

object Utils {

    fun String.toBookName(): String = this.toUpperCase().replace("_", " ")

}