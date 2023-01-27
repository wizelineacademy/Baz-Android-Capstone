package com.javg.cryptocurrencies.utils

object CRYUtils {
    fun separateStringCoin(book: String): String{
        return if (book.isNotEmpty()) book.split("_")[0] else ""
    }
}