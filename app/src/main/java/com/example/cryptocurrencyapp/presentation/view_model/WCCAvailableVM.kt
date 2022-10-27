package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.use_case.WCCAvailableUseCase
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.launch

class WCCAvailableVM(private val availableUseCase : WCCAvailableUseCase) : ViewModel() {


    private val _crypto = MutableLiveData<WCCryptoBookDTO>()
    val detail: LiveData<WCCryptoBookDTO> get() = _crypto

    fun getAvailableBook() {
        viewModelScope.launch {
            val response = availableUseCase.invoke()
            response.collect{
                when(it){
                    is Resource.Loading ->
                       Log.i("depur","cargando")
                    is Resource.Success ->{
                        Log.i("datos", "${it.data}")
                        // quiero llamar al caso de uso y pintar los datos en el log

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