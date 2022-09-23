package com.example.wizelineproject.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.wizelineproject.domain.model.Transaction
import com.example.wizelineproject.domain.repository.CriptosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BidsViewModel @Inject constructor() {

    @Inject
    lateinit var repository: CriptosRepository

    private val _bids = MutableLiveData<List<Transaction>?>()
    val asks = _bids

    fun getBids(book:String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getTransactions(book) { success, data ->
                if (success) {
                    _bids.postValue(data?.bids)
                } else
                    Log.e("log", "FALLO getBids ViewModel")
            }
        }
    }

}