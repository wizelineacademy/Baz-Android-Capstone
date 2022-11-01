package com.example.bazandroidcourse.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazandroidcourse.data.datasource.local.CryptoLocalDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.apiInstance
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryImpl
import com.example.bazandroidcourse.data.utils.network.NetworkManagerImpl
import com.example.bazandroidcourse.domain.GetAllBooksFilteredUseCase
import com.example.bazandroidcourse.domain.GetBookDetailUseCase
import com.example.bazandroidcourse.domain.GetBookOrdersUseCase
import kotlinx.coroutines.launch

class BooksViewModel(context: Context):ViewModel() {

    private val getBooksUseCase = GetAllBooksFilteredUseCase(
        BooksRepositoryImpl(
            NetworkManagerImpl(context),
            CryptoLocalDataSourceImpl(),
            CryptoRemoteDataSourceImpl(apiInstance)
        )
    )
    private val getBookDetailUseCase = GetBookDetailUseCase(
        BooksRepositoryImpl(
            NetworkManagerImpl(context),
            CryptoLocalDataSourceImpl(),
            CryptoRemoteDataSourceImpl(apiInstance)
        )
    )
    private val getBookOrdersUseCase = GetBookOrdersUseCase(
        BooksRepositoryImpl(
            NetworkManagerImpl(context),
            CryptoLocalDataSourceImpl(),
            CryptoRemoteDataSourceImpl(apiInstance)
        )
    )

    private val _allBooks = MutableLiveData<List<BookModel>>()
    val allBooks:MutableLiveData<List<BookModel>> = _allBooks

    private val _currentBook = MutableLiveData<BookDetailModel>()
    val currentBook:MutableLiveData<BookDetailModel> = _currentBook

    private val _currentBookOrders = MutableLiveData<BookOrdersModel>()
    val currentBookOrders: MutableLiveData<BookOrdersModel> = _currentBookOrders

    fun getAllBooks( currency:String){
        viewModelScope.launch{
             _allBooks.value = getBooksUseCase.invoke(currency)
        }
    }

    fun getBookDetail(bookId:String){
        viewModelScope.launch {
            _currentBook.value = getBookDetailUseCase.invoke(bookId)
        }
    }

    fun getBookOrders(bookId:String){
       viewModelScope.launch {
           _currentBookOrders.value = getBookOrdersUseCase.invoke(bookId)
       }
    }
}