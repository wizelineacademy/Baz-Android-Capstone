package com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.LocalOrderBookRepositoryImpl
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepositoryImpl
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases.GetBooksUseCase
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.BooksScreenState
import kotlinx.coroutines.launch

class AvailableBooksViewModel: ViewModel() {

    private val booksRepository by lazy { RemoteOrderBooksRepositoryImpl() }
    private val localOrderBookRepositoryImpl by lazy { LocalOrderBookRepositoryImpl() }

    val books: MutableLiveData<MutableList<ExchangeOrderBook>> by lazy {
        MutableLiveData<MutableList<ExchangeOrderBook>>()
    }

    var selectedBookOrder = ExchangeOrderBook()

    fun getBooks(){
        viewModelScope.launch{
            val response = booksRepository.getBooksFromApi()
            when(response){
                is BitsoApiResponseWrap.Success -> {
                    if(response.response.success == true){
                        log(message = "Hi array of books:-> ${response.response.payload.toString()}")
                    }
                }
                else -> {}
            }
        }
    }

    fun getBooksWithUseCase(){
        viewModelScope.launch {
            val booksState = GetBooksUseCase(
                booksRepository,
                localOrderBookRepositoryImpl
            ).invoke()
            when(booksState){
                is BooksScreenState.BooksSuccess ->{
                    val stuff = booksState.data
                    books.postValue(stuff)
                    log(message = "Success UseCase ${stuff.toString()}")
                }
                is BooksScreenState.BooksErrorOrEmpty -> {
                    log(message = "some error ${booksState.message}")
                }
            }
        }
    }
}