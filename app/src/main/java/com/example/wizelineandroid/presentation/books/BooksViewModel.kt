package com.example.wizelineandroid.presentation.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.repository.available.BooksRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class BooksViewModel @Inject constructor(private val repo: BooksRepo) : ViewModel() {

    // Usamos los estados de carga
    fun fetchBooks() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getAvailableBooks()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

