package com.example.wizelineproject.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wizelineproject.domain.model.OrderBook
import com.example.wizelineproject.domain.model.Transaction
import com.example.wizelineproject.domain.repository.CriptosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsksViewModel@Inject constructor(): ViewModel() {

    @Inject
    lateinit var repository: CriptosRepository

    private val _asks = MutableLiveData<List<Transaction>?>()
    val asks = _asks

    fun getAsks(book:String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getTransactions(book) { success, data ->
                if (success) {
                    _asks.postValue(data?.asks)
                } else
                    Log.e("log", "FALLO getAsks ViewModel")
            }
        }
    }
}