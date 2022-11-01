package com.example.bazandroidcourse.data.utils.mappers

import com.example.bazandroidcourse.data.datasource.remote.api.response.*
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrderResumeModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.entities.static.ApplicationCurrencies


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

fun OrderPayload.toDomain():BookOrdersModel{
    return BookOrdersModel(
       asks = bids?.toDomainList() ?: emptyList(),
       bids = bids?.toDomainList() ?: emptyList()
    )
}

fun List<OrderResume>.toDomainList():List<BookOrderResumeModel>{
    return map {
        it.toDomain()
    }
}

fun OrderResume.toDomain():BookOrderResumeModel{
    return BookOrderResumeModel(
        book = book.orEmpty(),
        amount = amount.orEmpty(),
        price =  price.orEmpty()
    )
}



