package com.proyect.cursowizline.view.cryptoList

import androidx.lifecycle.*
import com.proyect.cursowizline.database.entities.toDatabase
import com.proyect.cursowizline.domain.model.CryptoM
import com.proyect.cursowizline.model.Crypto
import com.proyect.cursowizline.model.ResponseStatus
import com.proyect.cursowizline.view.cryptoList.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private val cryptoRepository : CryptoRepository): ViewModel() {

    private val _cryptoList = MutableLiveData<List<CryptoM>>()
    val cryptoList: LiveData<List<CryptoM>>
        get() = _cryptoList

    private val _status = MutableLiveData<ResponseStatus<List<CryptoM>>>()
    val status: LiveData<ResponseStatus<List<CryptoM>>>
        get() = _status


 /*   val cryptoList = liveData(Dispatchers.IO){
        val listCrypto = cryptoRepository.downloadCrypto()
        emit(listCrypto)
    }

    val status = liveData(Dispatchers.IO){
        val okStatus = cryptoRepository.downloadCrypto()
        emit(okStatus)
    }
*/
    init {
        downloadCrypto()
    }

    private fun downloadCrypto() {
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            handleResponseStatus(cryptoRepository.downloadCrypto())
        }
    }

    private fun handleResponseStatus(responseStatus: ResponseStatus<List<CryptoM>>) {
        if (responseStatus is ResponseStatus.Success) {
            _cryptoList.value = responseStatus.payload
        }
        _status.value = responseStatus
    }

    suspend operator fun invoke():List<CryptoM>{
        val books = cryptoRepository.getAllCryptoFromDatabase()

        return if(books.isNotEmpty()){
            cryptoRepository.clearCrypto()
            cryptoRepository.insertCrypto(books.map { it.toDatabase() })
            books

        }else{
            cryptoRepository.getAllCryptoFromDatabase()
        }
    }



}