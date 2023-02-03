package com.lefg095.criptoone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lefg095.criptoone.domain.response.BaseResponse
import com.lefg095.criptoone.domain.stateevent.BooksStateEvent
import com.lefg095.criptoone.domain.stateevent.DataState
import com.lefg095.criptoone.data.interfaces.IBookRepository
import com.lefg095.criptoone.domain.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel
@Inject
constructor(
    private val booksIBookRepository: IBookRepository
): ViewModel() {

    private val _booksResponse = MutableLiveData<DataState<BaseResponse<Book>>>()
    val bookResponse: LiveData<DataState<BaseResponse<Book>>> get() = _booksResponse

    fun makeApiCall(booksStateEvent: BooksStateEvent){
        when(booksStateEvent){
            is BooksStateEvent.GetBooks -> {
                viewModelScope.launch {
                    booksIBookRepository.getBooks()
                        .collect {
                            _booksResponse.value = it
                        }
                }
            }
        }
    }
}