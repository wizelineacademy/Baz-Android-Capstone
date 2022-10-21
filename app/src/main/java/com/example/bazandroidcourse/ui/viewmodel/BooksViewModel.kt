package com.example.bazandroidcourse.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazandroidcourse.data.datasource.local.CryptoLocalDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.apiInstance
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryImpl
import com.example.bazandroidcourse.data.utils.network.NetworkManagerImpl
import com.example.bazandroidcourse.domain.GetAllBooksSortedUseCase
import kotlinx.coroutines.launch

class BooksViewModel(context: Context):ViewModel() {

    private val getBooksUseCase = GetAllBooksSortedUseCase(
         BooksRepositoryImpl(
            NetworkManagerImpl(context),
            CryptoLocalDataSourceImpl(),
            CryptoRemoteDataSourceImpl(apiInstance)
        )
    )

    private val _allBooks = MutableLiveData<List<BookModel>>()
    val allBooks = _allBooks

    fun getAllBooks(){
        viewModelScope.launch{
             _allBooks.value = getBooksUseCase.invoke()
        }
    }

}