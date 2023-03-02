package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.useCases

import com.axiasoft.android.zerocoins.common.emptyString
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.toDomain
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.ticker.Ticker
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates.TickerScreenState

class GetTickerUseCase(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepository: LocalOrderBookRepository,
) {
    suspend fun invoke(book: ExchangeOrderBook): TickerScreenState {
        val tickerWrappedResponse = remoteOrderBooksRepository.getTickerFromApi(book.book ?: "")
        return when (tickerWrappedResponse) {
            is BitsoApiResponseWrap.Success -> {
                if (tickerWrappedResponse.response.payload != null && tickerWrappedResponse.response.success == true) {
                    val ticker = tickerWrappedResponse.response.payload
                    val tickerDomain = ticker.toDomain()
                    updateTickerDb(tickerDomain)
                    TickerScreenState.TickerSuccess(tickerDomain)
                } else {
                    TickerScreenState.TickerError(
                        tickerWrappedResponse.response.error?.message ?: "",
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
        localOrderBookRepository.storeTickerDBWithScope(ticker)
    }
}
