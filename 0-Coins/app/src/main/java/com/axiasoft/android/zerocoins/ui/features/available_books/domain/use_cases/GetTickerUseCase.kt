package com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.book.response.Book
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.book_order.BooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.TickerScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap

class GetTickerUseCase(private val booksRepository: BooksRepository) {
    suspend fun invoke(book: Book): TickerScreenState {
        val tickerWrappedResponse = booksRepository.getTicketsFromApi(book.book ?: "")
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