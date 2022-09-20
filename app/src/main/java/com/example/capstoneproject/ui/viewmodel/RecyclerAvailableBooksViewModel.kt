package com.example.capstoneproject.ui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.model.AvailableBookModel
import com.example.capstoneproject.domain.GetAvailableBooksUseCase
import kotlinx.coroutines.launch

class RecyclerAvailableBooksViewModel : ViewModel() {
   private var getAvailableBooksUseCase = GetAvailableBooksUseCase()

    private var _availableBooks = MutableLiveData<List<AvailableBookModel>>()
    val availableBooks = _availableBooks

    private val _isLoading = MutableLiveData<Int>()
    var isLoading: LiveData<Int> = _isLoading

    fun onCreate(){
        viewModelScope.launch {
            _isLoading.postValue(View.VISIBLE)
            val result = getAvailableBooksUseCase()
            if(result.isNotEmpty()){
                _availableBooks.postValue(result)
                _isLoading.postValue(View.GONE)
            }

        }
    }

}