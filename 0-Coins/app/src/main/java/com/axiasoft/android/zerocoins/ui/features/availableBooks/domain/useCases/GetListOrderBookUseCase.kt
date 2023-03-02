package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.useCases

import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Ask
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Bids
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.orderBook.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.orderBook.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates.ListOrderBookScreenState

class GetListOrderBookUseCase(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepository: LocalOrderBookRepository,

) {
    suspend fun invoke(book: ExchangeOrderBook): ListOrderBookScreenState {
        val listOrderBookResponse = remoteOrderBooksRepository.getListOrderBook(book.book ?: "")
        return when (listOrderBookResponse) {
            is BitsoApiResponseWrap.Success -> {
                if (listOrderBookResponse.response.payload != null && listOrderBookResponse.response.success == true) {
                    val asks = (
                        listOrderBookResponse.response.payload.asks
                            ?: emptyList()
                        ) as ArrayList<Ask>
                    val bids = (
                        listOrderBookResponse.response.payload.bids
                            ?: emptyList()
                        ) as ArrayList<Bids>
                    updateDB(asks, bids)
                    ListOrderBookScreenState.Success(
                        asks = asks,
                        bids = bids,
                    )
                } else {
                    ListOrderBookScreenState.ErrorOrEmpty(
                        listOrderBookResponse.response.error?.message ?: "",
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
                bids = bids,
            )
        } else {
            ListOrderBookScreenState.ErrorOrEmpty("no data")
        }
    }

    private fun updateDB(asks: ArrayList<Ask>, bids: ArrayList<Bids>) {
        localOrderBookRepository.storeBidsAndAsks(asks, bids)
    }
}
