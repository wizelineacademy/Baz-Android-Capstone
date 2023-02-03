package com.example.wizelineandroid.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.repository.available.BooksRepo
import kotlinx.coroutines.Dispatchers

class booksViewModel (
    private val repo: BooksRepo
): ViewModel() {

    //Usamos los estados de carga
    fun fetchBooks() = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getAvailableBooks()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}

class BooksViewModelFactory(private val repo: BooksRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BooksRepo::class.java).newInstance(repo)
    }

}