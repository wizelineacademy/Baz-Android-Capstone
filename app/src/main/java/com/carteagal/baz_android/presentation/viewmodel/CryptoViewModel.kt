package com.carteagal.baz_android.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carteagal.baz_android.data.model.availableBook.AvailableBookResponse
import com.carteagal.baz_android.data.model.availableBook.AvailableBookUI
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.data.network.Resources.Error
import com.carteagal.baz_android.data.network.Resources.Success
import com.carteagal.baz_android.domain.useCase.GetAvailableBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val getAvailableBooksUseCase: GetAvailableBooksUseCase
): ViewModel(){

    private val _loading = MutableLiveData<Boolean>()
    private val _availableBooks = MutableLiveData<Resources<List<AvailableBookUI>?>>()

    val loading: LiveData<Boolean> get() = _loading
    val availableBooks: MutableLiveData<Resources<List<AvailableBookUI>?>> get() = _availableBooks

    fun getAvailableBooks() = viewModelScope.launch{
        _loading.postValue(true)
        val result = when(val data = getAvailableBooksUseCase()){
            is Success -> { Success(data.data) }
            is Error -> { Error(data.message) }
            else -> { Error(data.message) }
        }
        _availableBooks.postValue(result)
        _loading.postValue(false)
    }
}