package com.example.capstoneproject.data.model

import com.example.capstoneproject.data.model.availableBooks.AvailableBookModel
import com.example.capstoneproject.data.model.orderBook.OrderBookModel
import com.example.capstoneproject.data.model.ticker.TickerModel

class BitsoProvider {
    companion object {
        var availableBooks: List<AvailableBookModel> = emptyList()
        var ticker: TickerModel = TickerModel()
        var orderBook: OrderBookModel = OrderBookModel()
    }
}