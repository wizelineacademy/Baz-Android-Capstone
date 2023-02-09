package com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.order_book.response.Ask
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.order_book.response.Bids
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.BooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.ListOrderBookScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap

class GetListOrderBookUseCase(
    private val booksRepository: BooksRepository
    ) {
    suspend fun invoke(book: ExchangeOrderBook): ListOrderBookScreenState {
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