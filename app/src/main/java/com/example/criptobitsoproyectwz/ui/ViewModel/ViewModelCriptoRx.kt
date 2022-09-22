package com.example.criptobitsoproyectwz.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import com.example.criptobitsoproyectwz.data.network.CriptosClientRx
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModelCriptoRx(private val criptoClientRx: CriptosClientRx) : ViewModel() {

    private val _chritos = MutableStateFlow<List<Payload>>(emptyList())
    val chritos: StateFlow<List<Payload>> = _chritos

    init {
        getCritos()
    }

    private fun getCritos() {
        criptoClientRx
            .getCriptosRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ onSuccess: BaseResult?, onError: Throwable? ->
                onSuccess?.let {
                    _chritos.value = it.payload
                }

                onError?.let{
                    _chritos.value = emptyList()
                }

            }

    }

}

class CriptosViewModelFactory(private val cripClient: CriptosClientRx) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CriptosClientRx::class.java).newInstance(cripClient)
    }
}