package com.example.criptobitsoproyectwz.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.criptobitsoproyectwz.core.Resource
import com.example.criptobitsoproyectwz.data.Repository.useCaseCripto
import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import com.example.criptobitsoproyectwz.data.network.CriptosClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModelCriptoCall(private val criptoClient: CriptosClient): ViewModel() {
    private val _criptos = MutableStateFlow(emptyList<Payload>())
    val criptos: StateFlow<List<Payload>> = _criptos

    init {
        getCriptos()
    }

    private fun getCriptos() {
        criptoClient.getAllCriptos2 {
            _criptos.value = it
        }
    }
    fun getCriptosCall() = liveData(Dispatchers.IO){
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

class CriosVMCal(private val criptoClient: CriptosClient): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CriptosClient::class.java).newInstance(criptoClient)
    }
}