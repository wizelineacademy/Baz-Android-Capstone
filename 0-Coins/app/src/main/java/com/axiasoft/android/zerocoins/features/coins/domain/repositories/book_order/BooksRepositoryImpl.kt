package com.axiasoft.android.zerocoins.features.coins.domain.repositories.book_order

import com.axiasoft.android.zerocoins.features.coins.domain.apis.BooksApi
import com.axiasoft.android.zerocoins.features.coins.domain.models.data.book.response.Book
import com.axiasoft.android.zerocoins.features.coins.domain.models.data.ticker.response.Ticker
import com.axiasoft.android.zerocoins.features.coins.domain.repositories.book_order.BooksRepository
import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiCallWrapper
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import kotlinx.coroutines.Dispatchers

class BooksRepositoryImpl: BooksRepository {

    private val bitsoBooksApi: BooksApi by lazy { BooksApi.Builder().build() }

    override suspend fun getBooksFromApi(): BitsoApiResponseWrap<BitsoBaseResponse<ArrayList<Book>>> {
        return BitsoApiCallWrapper.callBitsoApiWrap(dispatcher = Dispatchers.IO){
            bitsoBooksApi.getBooksFromApi()
        }
    }

    override suspend fun getTicketsFromApi(book: String): BitsoApiResponseWrap<BitsoBaseResponse<Ticker>> {
        return BitsoApiCallWrapper.callBitsoApiWrap(dispatcher = Dispatchers.IO){
            bitsoBooksApi.getTicketsApi(book)
        }
    }
}