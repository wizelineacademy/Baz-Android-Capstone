package com.capstone.capstonecoins.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.domain.api.usecases.AvailableBooksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinViewmodel(private var useCase: AvailableBooksUseCase) : ViewModel() {
    val cryptoBook = MutableLiveData<List<Book>>()

    fun getAvailableBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.book()
            response.collect { books ->
                cryptoBook.postValue(books)
            }
        }
    }

}

class ViewModelFactory(private val availableUseCase: AvailableBooksUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CoinViewmodel(availableUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}