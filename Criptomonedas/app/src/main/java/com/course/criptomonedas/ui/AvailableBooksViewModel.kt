package com.course.criptomonedas.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.viewModelScope
import com.course.criptomonedas.data.models.ModelBook
import com.course.criptomonedas.domain.GetAvailableBooksCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AvailableBooksViewModel(
    private val useCaseGetAvailableBooksCase: GetAvailableBooksCase
) : ViewModel() {

    val _available_books = MutableLiveData<List<ModelBook>>()
    val availableBooks = _available_books

    fun getAvailableBooks() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = useCaseGetAvailableBooksCase()
            _available_books.postValue(result)
        } catch (e: Exception) {
            Log.d("TAG_FRANK", "getAvailableBooks: Error")
        }
    }
}

class MainViewModelFactory(private val useCaseGetAvailableBooksCase: GetAvailableBooksCase) :
    Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetAvailableBooksCase::class.java)
            .newInstance(useCaseGetAvailableBooksCase)
    }
}