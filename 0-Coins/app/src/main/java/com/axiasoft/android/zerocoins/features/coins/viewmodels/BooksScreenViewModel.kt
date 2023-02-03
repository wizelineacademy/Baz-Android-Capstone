package com.axiasoft.android.zerocoins.features.coins.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.features.coins.domain.models.data.book.response.Book
import kotlinx.coroutines.launch
import com.axiasoft.android.zerocoins.features.coins.domain.repositories.book_order.BooksRepositoryImpl
import com.axiasoft.android.zerocoins.features.coins.domain.use_cases.GetBooksUseCase
import com.axiasoft.android.zerocoins.features.coins.domain.use_cases.GetTickerUseCase
import com.axiasoft.android.zerocoins.features.coins.views.ui_states.BooksScreenState
import com.axiasoft.android.zerocoins.features.coins.views.ui_states.TickerScreenState
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import kotlinx.coroutines.Dispatchers

class BooksScreenViewModel: ViewModel() {

    private val dispatcher = Dispatchers.IO

    private val booksRepository by lazy { BooksRepositoryImpl() }

    val books: MutableLiveData<MutableList<Book>> by lazy {
        MutableLiveData<MutableList<Book>>()
    }

    var selectedBookOrder = Book()

    var tickerState: MutableLiveData<TickerScreenState> = MutableLiveData()

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
            val booksState = GetBooksUseCase(booksRepository).invoke()
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

    fun getTickerWithUseCase(){
        viewModelScope.launch {
            val tickerSreenState = GetTickerUseCase(booksRepository).invoke(selectedBookOrder)
            when(tickerSreenState){
                is TickerScreenState.TickerSuccess -> {
                    val ticker = tickerSreenState.ticker
                    tickerState.value = TickerScreenState.TickerSuccess(ticker)
                }
                is TickerScreenState.TickerError -> {

                }
            }
        }
    }
}