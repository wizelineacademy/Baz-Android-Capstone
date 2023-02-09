package com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.BooksScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.toDomain
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.LocalOrderBookRepositoryImpl

class GetBooksUseCase(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepositoryImpl: LocalOrderBookRepositoryImpl
) {
    suspend operator fun invoke(): BooksScreenState /*: state*/{
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

    suspend fun updateDBAvailableExchangeOrderBook(availableExchangeOrderBook: ArrayList<ExchangeOrderBook>){
        //val localOrderBookRepositoryImpl = LocalOrderBookRepositoryImpl()
        localOrderBookRepositoryImpl.storeAvailableExchangeOrderBooks(availableExchangeOrderBook)
    }
}