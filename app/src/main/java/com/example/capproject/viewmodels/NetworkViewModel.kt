package com.example.capproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capproject.datastore.DataStoreRepository
import kotlinx.coroutines.DelicateCoroutinesApi

import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext



class NetworkViewModel (private val StatesRepository: DataStoreRepository):ViewModel()
{
    @OptIn(DelicateCoroutinesApi::class)
    fun setNetworkState(key1:String, key2:String){
        viewModelScope.launch(newSingleThreadContext("Networks")) {
            StatesRepository.setNetworkStatus(key1,key2)

        }
    }
}