package com.axiasoft.android.zerocoins.features.available_books.domain.repositories.book_order

import com.axiasoft.android.zerocoins.features.available_books.domain.models.data.book.response.Book
import com.axiasoft.android.zerocoins.features.available_books.domain.models.data.order_book.response.ListOrderBookResponse
import com.axiasoft.android.zerocoins.features.available_books.domain.models.data.ticker.response.Ticker
import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap

interface BooksRepository {
    suspend fun getBooksFromApi(): BitsoApiResponseWrap<BitsoBaseResponse<ArrayList<Book>>>

    suspend fun getTicketsFromApi(book: String): BitsoApiResponseWrap<BitsoBaseResponse<Ticker>>

    suspend fun getListOrderBook(book: String): BitsoApiResponseWrap<BitsoBaseResponse<ListOrderBookResponse>>
}