package com.example.criptobitsoproyectwz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptobitsoproyectwz.data.model.BaseResult
import com.example.criptobitsoproyectwz.data.Repository.useCaseCripto
import com.example.criptobitsoproyectwz.data.model.Payload
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ViewModelCripto: ViewModel() {
    /*** ViewModel
     *  Conexión entre el modelo y la vista.
     * Las vistas se suscriben a sus viewModels y
     * estos al notar de que el modelo ha sido modificado lo notificarán a la vista.
     */

    private val _dataCripto = MutableStateFlow(emptyList<Payload>())
    val dataCripto = _dataCripto

  init{
        viewModelScope.launch {
            val result = useCaseCripto().invoke()
            if (result?.exitoso == true){
                _dataCripto.value = result.payload
            }
        }
    }

}