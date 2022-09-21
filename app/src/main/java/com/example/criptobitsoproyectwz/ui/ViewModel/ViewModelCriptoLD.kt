package com.example.criptobitsoproyectwz.ui.ViewModel

import androidx.lifecycle.*
import com.example.criptobitsoproyectwz.core.Resource
import com.example.criptobitsoproyectwz.data.Repository.useCaseCripto
import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelCriptoLD: ViewModel() {

    private val _ListCripto = MutableLiveData<List<Payload>>()
    val listCripto : LiveData<List<Payload>> = _ListCripto

    fun getCriptosLD1(){
        viewModelScope.launch {
            val result = useCaseCripto().invoke()
            if (result?.exitoso == true) {
                _ListCripto.value = result.payload.filter { it.book.contains("mxn") }
            }
        }
    }

    fun getCriptosLD() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            val result = useCaseCripto().invoke()
            if (result?.exitoso == true) {
                emit(Resource.Succes(result.payload))
            }

        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}