package com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases

import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Ask
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Bids
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.LocalOrderBookRepositoryImpl
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.ListOrderBookScreenState

class GetListOrderBookUseCase(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepository: LocalOrderBookRepositoryImpl

) {
    suspend fun invoke(book: ExchangeOrderBook): ListOrderBookScreenState {
        val listOrderBookResponse = remoteOrderBooksRepository.getListOrderBook(book.book ?: "")
        return when (listOrderBookResponse) {
            is BitsoApiResponseWrap.Success -> {
                if (listOrderBookResponse.response.payload != null && listOrderBookResponse.response.success == true) {
                    val asks = (listOrderBookResponse.response.payload.asks
                        ?: emptyList()) as ArrayList<Ask>
                    val bids = (listOrderBookResponse.response.payload.bids
                        ?: emptyList()) as ArrayList<Bids>
                    updateDB(asks, bids)
                    ListOrderBookScreenState.Success(
                        asks = asks,
                        bids = bids
                    )
                } else {
                    ListOrderBookScreenState.ErrorOrEmpty(
                        listOrderBookResponse.response.error?.message ?: ""
                    )
                }
            }
            else -> {
                ListOrderBookScreenState.ErrorOrEmpty()
            }
        }
    }

    suspend fun retrieveAskAndBids(book: ExchangeOrderBook): ListOrderBookScreenState {
        val pairFromDb = localOrderBookRepository.retrieveBidsAndAsks(book.book ?: "")
        val asks = pairFromDb.first as ArrayList
        val bids = pairFromDb.second as ArrayList
        return if (asks.isNotEmpty() && bids.isNotEmpty()) {
            ListOrderBookScreenState.Success(
                asks = asks,
                bids = bids
            )
        } else {
            ListOrderBookScreenState.ErrorOrEmpty("no data")
        }
    }

    private fun updateDB(asks: ArrayList<Ask>, bids: ArrayList<Bids>) {
        localOrderBookRepository.storeBidsAndAsks(asks, bids)
    }
}