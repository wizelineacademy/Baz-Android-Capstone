package com.example.criptobitsoproyectwz.ui.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.criptobitsoproyectwz.data.Repository.useCaseCripto
import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import com.example.criptobitsoproyectwz.data.model.OrderBook.Asks
import com.example.criptobitsoproyectwz.data.model.OrderBook.Bids
import com.example.criptobitsoproyectwz.data.model.OrderBook.PayloadBookOrder
import com.example.criptobitsoproyectwz.data.model.Ticket.PayloadCripto
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

    private val _dataCriptoInfo = MutableStateFlow(emptyList<PayloadCripto>())
    val dataCriptoInfo = _dataCriptoInfo

    private val _dataAsk = MutableStateFlow(emptyList<Asks>())
    val dataAsks = _dataAsk

    private val _dataBids = MutableStateFlow(emptyList<Bids>())
    val dataBids = _dataBids

    private val _dataBidsAsks = MutableStateFlow(emptyList<PayloadBookOrder>())
    val dataBidsAsks = _dataBidsAsks


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

    fun getCriptosInfo(cripto: String){
        viewModelScope.launch {
            val result = useCaseCripto().useCaseInfoCripto(crip = cripto)
            if (result?.success == true){
                dataCriptoInfo.value = listOf(result.payload)
            }
        }
    }

    fun getAskBids(cripto: String){
        viewModelScope.launch {
            val result = useCaseCripto().useCaseAskBids(crip = cripto)
            if (result?.exitoso == true){
                dataAsks.value = result.payload.asks
                dataBids.value = result.payload.bids
            }
        }
    }
    fun getAllAskBids(cripto: String){
        viewModelScope.launch {
            val result = useCaseCripto().useCaseAskBids(crip = cripto)
            if (result?.exitoso == true){
                dataBidsAsks.value = listOf(result.payload)
            }
        }
    }


}