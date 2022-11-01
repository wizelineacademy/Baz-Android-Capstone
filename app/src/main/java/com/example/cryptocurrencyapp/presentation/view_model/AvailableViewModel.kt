package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.use_case.WCCAvailableUseCase
import com.example.cryptocurrencyapp.utils.Resource
import com.example.cryptocurrencyapp.utils.CryptoConstants
import kotlinx.coroutines.launch

class AvailableViewModel(private val availableUseCase: WCCAvailableUseCase) : ViewModel() {

    private val _cryptoBook = MutableLiveData<List<WCCryptoBookDTO>>()
    val coins: LiveData<List<WCCryptoBookDTO>> get() = _cryptoBook

    private var _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getAvailableBook() {
        viewModelScope.launch {
            val response = availableUseCase.coin()
            response.collect { coins ->
                when (coins) {
                    is Resource.Loading ->
                        _isLoading.value = true
                    is Resource.Success -> {
                        _isLoading.value = false
                        coins.data?.filter { coin ->
                            coin.book.contains(CryptoConstants.MXN)
                        }?.map {
                            WCCryptoBookDTO(
                                book = it.book,
                                minPrice = it.minPrice,
                                maxPrice = it.maxPrice,
                                name = it.name,
                                logo = it.logo
                            )
                        } ?.let {
                            _cryptoBook.value = it
                        }
                    }
                    is Resource.Error ->
                        Log.i("depur", "Error")
                }
            }
        }
    }
}

class ViewModelFactory(private val availableUseCase: WCCAvailableUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AvailableViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AvailableViewModel(availableUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}