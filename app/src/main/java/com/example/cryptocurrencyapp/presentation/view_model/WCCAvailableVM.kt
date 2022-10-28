package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.use_case.WCCAvailableUseCase
import com.example.cryptocurrencyapp.utils.Resource
import com.example.cryptocurrencyapp.utils.CryptoConstants
import kotlinx.coroutines.launch

class WCCAvailableVM(private val availableUseCase : WCCAvailableUseCase) : ViewModel() {


    private val _crypto = MutableLiveData<WCCryptoBookDTO>()
    val detail: LiveData<WCCryptoBookDTO> get() = _crypto

    fun getAvailableBook() {
        viewModelScope.launch {
            val response = availableUseCase.coin()
            response.collect{ coins ->
                when(coins){
                    is Resource.Loading ->
                       Log.i("depur","cargando")
                    is Resource.Success ->{
                        val filteredCoin = coins.data?.filter { coin ->
                            coin.book.contains(CryptoConstants.MXN)
                        }
                        Log.i("datos", "$filteredCoin")
                    }
                    is Resource.Error ->
                        Log.i("depur","Error")
                }
            }
        }
    }

}

class ViewModelFactory(private val availableUseCase : WCCAvailableUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WCCAvailableVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WCCAvailableVM(availableUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}