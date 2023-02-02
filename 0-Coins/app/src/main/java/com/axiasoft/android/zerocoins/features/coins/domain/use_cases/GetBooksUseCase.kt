package com.axiasoft.android.zerocoins.features.coins.domain.use_cases

import com.axiasoft.android.zerocoins.features.coins.domain.repositories.BooksRepository
import com.axiasoft.android.zerocoins.features.coins.views.ui_states.BooksScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap

class GetBooksUseCase(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(): BooksScreenState /*: state*/{
        val booksWrappedResponse = booksRepository.getBooksFromApi()
        return when(booksWrappedResponse){
            is BitsoApiResponseWrap.Success -> {
                val booksArray = booksWrappedResponse.response.payload
                if (booksArray.isNullOrEmpty()){
                    BooksScreenState.BooksErrorOrEmpty()
                }else{
                    BooksScreenState.BooksSuccess(booksArray)
                }
            }
            else -> {
                BooksScreenState.BooksErrorOrEmpty()
            }
        }
    }
}