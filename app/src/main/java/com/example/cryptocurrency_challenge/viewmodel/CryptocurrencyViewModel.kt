package com.example.cryptocurrency_challenge.viewmodel

import androidx.lifecycle.*
import com.example.cryptocurrency_challenge.domain.AvailableBooksUseCase
import com.example.cryptocurrency_challenge.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyViewModel @Inject constructor (private val availableBooksUseCase : AvailableBooksUseCase): ViewModel(){

    private val _availableBookModel : MutableLiveData<AvailableBooksUiState>  = MutableLiveData()
    private val _isLoading          : MutableLiveData<Boolean> = MutableLiveData()

    val isLoading           : LiveData<Boolean> = _isLoading
    val availableBookModel  : LiveData<AvailableBooksUiState> = _availableBookModel

    fun getAvailableBooks(){
        viewModelScope.launch {
                val result = availableBooksUseCase.invoke()
                result.let {
                    _isLoading.postValue(false)
                    _availableBookModel.value = AvailableBooksUiState(payLoadList = result)
                }
        }
    }
}


