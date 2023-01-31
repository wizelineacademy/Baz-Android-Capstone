package com.jpgl.cryptocurrencies.data
import com.jpgl.cryptocurrencies.data.model.BidsModel
import com.jpgl.cryptocurrencies.data.model.BookModel
import com.jpgl.cryptocurrencies.data.model.TickerModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoProvider @Inject constructor() {
    var books: List<BookModel> = emptyList()
    var bids: List<BidsModel> = emptyList()
    var ticker: TickerModel? = null
}