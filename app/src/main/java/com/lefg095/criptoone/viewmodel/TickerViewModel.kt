package com.lefg095.criptoone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lefg095.criptoone.data.interfaces.ITickerRepository
import com.lefg095.criptoone.domain.response.TickerResponse
import com.lefg095.criptoone.domain.stateevent.DataState
import com.lefg095.criptoone.domain.stateevent.TickerStateEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel
@Inject
constructor(
    private val tickerIRepository: ITickerRepository
): ViewModel(){

    private val _tickerResponse = MutableLiveData<DataState<TickerResponse>>()
    val tickerResponse: LiveData<DataState<TickerResponse>> get() = _tickerResponse

    fun makeApiCall(tickerStateEvent: TickerStateEvent){
        when(tickerStateEvent){
            is TickerStateEvent.GetTicker -> {
                viewModelScope.launch {
                    tickerIRepository.getTicker(
                        tickerStateEvent.nameBook
                    ).collect {
                        _tickerResponse.value = it
                    }
                }
            }
        }
    }}