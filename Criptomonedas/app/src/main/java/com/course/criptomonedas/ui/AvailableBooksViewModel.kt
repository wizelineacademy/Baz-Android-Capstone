package com.course.criptomonedas.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.viewModelScope
import com.course.criptomonedas.data.models.AvailableBooks
import com.course.criptomonedas.data.repository.AvailableBooksRepository
import com.course.criptomonedas.domain.GetAvailableBooksCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AvailableBooksViewModel(
    private val repository: AvailableBooksRepository
) : ViewModel() {

    val _available_books = MutableLiveData<AvailableBooks>()
    val availableBooks = _available_books

    var getAvailableBooksCase = GetAvailableBooksCase()

    fun getAvailableBooks() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = repository.getAvailableBooks()
            _available_books.postValue(result)
        } catch (e: Exception) {
            Log.d("TAG_FRANK", "getAvailableBooks: Error")
        }
    }

}

class MainViewModelFactory(private val repository: AvailableBooksRepository) : Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AvailableBooksRepository::class.java)
            .newInstance(repository)
    }
}