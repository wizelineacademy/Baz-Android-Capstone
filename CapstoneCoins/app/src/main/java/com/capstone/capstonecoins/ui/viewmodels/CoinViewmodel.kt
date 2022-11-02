package com.capstone.capstonecoins.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.domain.api.usecases.AvailableBooksUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinViewmodel(private var useCase: AvailableBooksUseCase) : ViewModel() {
    val cryptoBook = MutableLiveData<BooksDto>()

    fun getAvailableBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = useCase.book()
            response.collect { book ->
                Log.d("Mensaje", "$book")
                cryptoBook.value = BooksDto(payload = book.payload, success = book.success)
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