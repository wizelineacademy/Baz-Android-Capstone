package com.example.wizelineproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizelineproject.domain.model.Book
import com.example.wizelineproject.domain.model.Ticker
import com.example.wizelineproject.domain.repository.CriptosRepository
import com.example.wizelineproject.domain.repository.GenericRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var repository:CriptosRepository

    private val _monedas = MutableLiveData<List<Book>>()
    val monedas =  _monedas

    private val _ticker = MutableLiveData<Ticker>()
    val ticker = _ticker

    fun getTicker(ticker:String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getTicker(ticker){ success, data ->
                if(success){
                    _ticker.postValue(data)
                } else{
                    Log.e("log", "FALLO getTicker ViewModel")
                }
            }
        }
    }

    fun getBooks(){
        /*viewModelScope.launch {  }*/
        CoroutineScope(Dispatchers.IO).launch {
            repository.getBooks { success, data ->
                if (success) {
                    _monedas.postValue(data)
                } else
                    Log.e("log", "FALLO getBooks ViewModel")
            }
        }
    }

    /*fun getBook(element:Int):Book{
        _monedas?.let {
            _monedas.value?.let {
                if(it.size>0 && element<it.size)
                    return it.get(element)
            }
        }
        return Book("empty", "0.0", "0.0","0.0","0.0", "0.0","0.0")
    }*/

}