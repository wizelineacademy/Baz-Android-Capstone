package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderBookResponse
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.TickerInfoModel

interface BookDataSourceInterface {
    fun fetchBooks():List<BookModel>
    fun fetchTickerInfo(book:String):List<TickerInfoModel>
    fun fetchResumeBooks(book:String):List<OrderBookResponse.BookResume>
}