package com.example.bazandroidcourse.data.utils.mappers

import com.example.bazandroidcourse.data.datasource.remote.api.response.Book
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookDetailResponse
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel


fun BookDetailResponse.BookDetail.toDomain(): BookDetailModel {
    return BookDetailModel(
        book    = book.orEmpty(),
        volume  = volume.orEmpty(),
        high    = high.orEmpty(),
        last    = last.orEmpty(),
        low     = low.orEmpty(),
        vwap    = vwap.orEmpty(),
        ask     = ask.orEmpty(),
        bid     = bid.orEmpty(),
        create  = create.orEmpty()
    )
}

fun Book.toDomain(): BookModel {
    return BookModel(
        book            = book.orEmpty(),
        minimumAmount   = minimumAmount.orEmpty(),
        maximumAmount   = maximumAmount.orEmpty(),
        minimumPrice    = minimumPrice.orEmpty(),
        maximumPrice    = maximumPrice.orEmpty(),
        minimumValue    = minimumValue.orEmpty(),
        maximumValue    = maximumValue.orEmpty(),
    )
}

fun List<Book>.toDomain():List<BookModel>{
    return map {
        it.toDomain()
    }
}

fun String.getCryptoName():String{
    return when {
        startsWith("btc")->"Bitcoin"
        startsWith("eth")->"Ethereum"
        startsWith("xrp")->"Ripple"
        startsWith("ltc")->"Litecoin"
        startsWith("bch")->"Bitcoin Cash"
        startsWith("tusd")->"TrueUSD"
        startsWith("mana")->"Decentraland"
        startsWith("bat")->"Basic Attention Token"
        startsWith("aave")->"AAVE"
        else ->this
    }
}

