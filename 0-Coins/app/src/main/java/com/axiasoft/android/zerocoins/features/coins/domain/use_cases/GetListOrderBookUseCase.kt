package com.axiasoft.android.zerocoins.features.coins.domain.use_cases

import com.axiasoft.android.zerocoins.features.coins.domain.models.data.book.response.Book
import com.axiasoft.android.zerocoins.features.coins.domain.models.data.order_book.response.Ask
import com.axiasoft.android.zerocoins.features.coins.domain.models.data.order_book.response.Bids
import com.axiasoft.android.zerocoins.features.coins.domain.repositories.book_order.BooksRepository
import com.axiasoft.android.zerocoins.features.coins.views.ui_states.ListOrderBookScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap

class GetListOrderBookUseCase(
    private val booksRepository: BooksRepository
    ) {
    suspend fun invoke(book: Book): ListOrderBookScreenState{
        val listOrderBookResponse = booksRepository.getListOrderBook(book.book ?: "")
        return when(listOrderBookResponse){
            is BitsoApiResponseWrap.Success -> {
                if (listOrderBookResponse.response.payload != null && listOrderBookResponse.response.success == true){
                    ListOrderBookScreenState.Success(
                        asks = (listOrderBookResponse.response.payload.asks ?: emptyList()) as ArrayList<Ask>,
                        bids = (listOrderBookResponse.response.payload.bids ?: emptyList()) as ArrayList<Bids>
                    )
                }else {
                    ListOrderBookScreenState.ErrorOrEmpty(listOrderBookResponse.response.error?.message ?: "")
                }
            } else -> {
                ListOrderBookScreenState.ErrorOrEmpty()
            }
        }
    }
}