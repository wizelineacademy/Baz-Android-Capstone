package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.myapplication.core.Resource
import com.example.myapplication.repository.BitsoRepository
import kotlinx.coroutines.Dispatchers

class BitsoViewModel(private val repo: BitsoRepository): ViewModel() {

    fun fetchAvailiableBooks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getAvailableBooks()))

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class BitsoViewModelFactory(private val repo: BitsoRepository): ViewModelProvider.Factory{

    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BitsoRepository::class.java).newInstance(repo)
    }
}