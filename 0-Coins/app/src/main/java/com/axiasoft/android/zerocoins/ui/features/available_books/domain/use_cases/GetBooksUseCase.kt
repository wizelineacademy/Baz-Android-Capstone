package com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.BooksScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.toDomain

class GetBooksUseCase(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository
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
                    BooksScreenState.BooksSuccess(availableExchangeOrderBooks)
                }
            }
            else -> {
                BooksScreenState.BooksErrorOrEmpty()
            }
        }
    }
}