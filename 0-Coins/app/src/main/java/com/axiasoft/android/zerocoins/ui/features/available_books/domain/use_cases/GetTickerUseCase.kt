package com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases

import com.axiasoft.android.zerocoins.common.emptyString
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.LocalOrderBookRepositoryImpl
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.TickerScreenState

class GetTickerUseCase(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepository: LocalOrderBookRepositoryImpl
) {
    suspend fun invoke(book: ExchangeOrderBook): TickerScreenState {
        val tickerWrappedResponse = remoteOrderBooksRepository.getTicketsFromApi(book.book ?: "")
        return when (tickerWrappedResponse) {
            is BitsoApiResponseWrap.Success -> {
                if (tickerWrappedResponse.response.payload != null && tickerWrappedResponse.response.success == true) {
                    val ticker = tickerWrappedResponse.response.payload
                    updateTickerDb(ticker)
                    TickerScreenState.TickerSuccess(ticker)
                } else {
                    TickerScreenState.TickerError(
                        tickerWrappedResponse.response.error?.message ?: ""
                    )
                }
            }
            else -> {
                TickerScreenState.TickerError()
            }
        }
    }

    suspend fun retrieveTicker(book: ExchangeOrderBook): TickerScreenState {
        val ticker = localOrderBookRepository.retrieveTicker(book.book ?: emptyString())
        return if (ticker != null) {
            TickerScreenState.TickerSuccess(ticker)
        } else {
            TickerScreenState.TickerError()
        }
    }

    private fun updateTickerDb(ticker: Ticker) {
        localOrderBookRepository.updateTickerDBWithScope(ticker)
    }
}