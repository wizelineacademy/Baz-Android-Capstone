package com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.TickerScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap

class GetTickerUseCase(private val remoteOrderBooksRepository: RemoteOrderBooksRepository) {
    suspend fun invoke(book: ExchangeOrderBook): TickerScreenState {
        val tickerWrappedResponse = remoteOrderBooksRepository.getTicketsFromApi(book.book ?: "")
        return when(tickerWrappedResponse){
            is BitsoApiResponseWrap.Success -> {
                if (tickerWrappedResponse.response.payload != null && tickerWrappedResponse.response.success == true){
                    TickerScreenState.TickerSuccess(tickerWrappedResponse.response.payload)
                }else {
                    TickerScreenState.TickerError(tickerWrappedResponse.response.error?.message ?: "")
                }
            } else -> {
                TickerScreenState.TickerError()
            }
        }
    }
}