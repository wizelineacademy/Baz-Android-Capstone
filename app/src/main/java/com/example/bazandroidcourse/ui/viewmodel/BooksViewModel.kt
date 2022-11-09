package com.example.bazandroidcourse.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazandroidcourse.data.datasource.local.database.room.BookDetailsLocalDatasourceImpl
import com.example.bazandroidcourse.data.datasource.local.database.room.BookOrdersLocalDataSourceImpl
import com.example.bazandroidcourse.data.datasource.local.database.room.BooksLocalDataSourceImpl
import com.example.bazandroidcourse.data.datasource.local.database.room.core.AppDataBase
import com.example.bazandroidcourse.data.datasource.local.database.room.core.dataBaseHelper
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.apiInstance
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryImpl
import com.example.bazandroidcourse.domain.GetAllBooksFilteredUseCase
import com.example.bazandroidcourse.domain.GetBookDetailUseCase
import com.example.bazandroidcourse.domain.GetBookOrdersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val getBooksUseCase: GetAllBooksFilteredUseCase,
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val getBookOrdersUseCase: GetBookOrdersUseCase

) : ViewModel() {
    companion object {
        fun createInstance(): BooksViewModel {
            val databaseHelper: AppDataBase = dataBaseHelper.getInstance()!!
            val repository = BooksRepositoryImpl(
                BooksLocalDataSourceImpl(databaseHelper.booksDao()),
                BookDetailsLocalDatasourceImpl(databaseHelper.bookDetailsDao()),
                BookOrdersLocalDataSourceImpl(databaseHelper.BookOrdersDao()),
                CryptoRemoteDataSourceImpl(apiInstance)
            )
            return BooksViewModel(
                GetAllBooksFilteredUseCase(repository),
                GetBookDetailUseCase(repository),
                GetBookOrdersUseCase(repository)
            )
        }
    }

    private val _allBooks = MutableLiveData<List<BookModel>>()
    val allBooks: MutableLiveData<List<BookModel>> = _allBooks

    private val _currentBook = MutableLiveData<BookDetailModel>()
    val currentBook: MutableLiveData<BookDetailModel> = _currentBook

    private val _currentBookOrders = MutableLiveData<BookOrdersModel>()
    val currentBookOrders: MutableLiveData<BookOrdersModel> = _currentBookOrders

    fun getAllBooks(currency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _allBooks.postValue(
                getBooksUseCase.invoke(currency)
            )
        }
    }

    fun getBookDetail(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentBook.postValue(
                getBookDetailUseCase.invoke(bookId)
            )
        }
    }

    fun getBookOrders(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentBookOrders.postValue(
                getBookOrdersUseCase.invoke(bookId)
            )
        }
    }
}
