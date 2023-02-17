package com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.BooksScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.toDomain
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.LocalOrderBookRepositoryImpl

class GetBooksUseCase(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepository: LocalOrderBookRepository
) {
    suspend operator fun invoke(): BooksScreenState {
        val booksWrappedResponse = remoteOrderBooksRepository.getBooksFromApi()
        return when(booksWrappedResponse){
            is BitsoApiResponseWrap.Success -> {
                val booksArray = booksWrappedResponse.response.payload
                if (booksArray.isNullOrEmpty()){
                    BooksScreenState.BooksErrorOrEmpty()
                }else{
                    val availableExchangeOrderBooks = ArrayList(
                        booksArray.map { it.toDomain() }
                    )
                    updateDBAvailableExchangeOrderBook(availableExchangeOrderBooks)
                    BooksScreenState.BooksSuccess(availableExchangeOrderBooks)
                }
            }
            else -> {
                BooksScreenState.BooksErrorOrEmpty()
            }
        }
    }

    suspend fun retrieveExchangeOrderBook(): BooksScreenState{
        val localExchangeOrderBooks = localOrderBookRepository.retrieveExchangeOrderBooks()
        return if (localExchangeOrderBooks.isNotEmpty()){
            BooksScreenState.BooksSuccess(localExchangeOrderBooks)
        } else {
            BooksScreenState.BooksErrorOrEmpty()
        }
    }

    private suspend fun updateDBAvailableExchangeOrderBook(availableExchangeOrderBook: ArrayList<ExchangeOrderBook>){
        localOrderBookRepository.storeAvailableExchangeOrderBooks(availableExchangeOrderBook)
    }
}