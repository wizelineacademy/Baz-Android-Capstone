package com.example.criptobitsoproyectwz.ui.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.criptobitsoproyectwz.data.Repository.useCaseCripto
import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/***ViewModel
 * Conexión entre el modelo y la vista.
 * Las vistas se suscriben a sus viewModels y
 * estos al notar de que el modelo ha sido modificado lo notificarán a la vista.
 */
class ViewModelCripto: ViewModel() {

    private val _dataCripto = MutableStateFlow(emptyList<Payload>())
    val dataCripto = _dataCripto


    init {
        viewModelScope.launch {
            val result = useCaseCripto().invoke()
            if (result?.exitoso == true) {
                _dataCripto.value = result.payload.filter { it.book.contains("mxn") }
            }
        }
    }


    /*** Flow
     * Cualquier objeto que permita que Jetpack Compose se adhiera a
     * cada cambio puede convertirse en State<T> y leerse mediante un elemento que admite composición.
     * manipular informacion, Capa de presentacion
     */

/*    private val _dataCripto = MutableLiveData<List<Payload>>()
    var dataCripto: LiveData<List<Payload>> = _dataCripto


    fun getCriptosList(){
        viewModelScope.launch {
            val result = useCaseCripto().invoke()
            if (result?.exitoso == true){
                _dataCripto.value = result.payload
            }
        }
    }*/





}