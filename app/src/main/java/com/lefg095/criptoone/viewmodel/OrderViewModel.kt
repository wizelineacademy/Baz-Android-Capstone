package com.lefg095.criptoone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lefg095.criptoone.data.interfaces.IOrderRepository
import com.lefg095.criptoone.domain.response.OrderResponse
import com.lefg095.criptoone.domain.stateevent.DataState
import com.lefg095.criptoone.domain.stateevent.OrderStateEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel
@Inject
constructor(
    private val orderIRepository: IOrderRepository
): ViewModel(){

    private val _orderResponse = MutableLiveData<DataState<OrderResponse>>()
    val orderResponse: LiveData<DataState<OrderResponse>> get() = _orderResponse

    fun makeApiCall(orderStateEvent: OrderStateEvent){
        when(orderStateEvent){
            is OrderStateEvent.GetOrder -> {
                viewModelScope.launch {
                    orderIRepository.getOrder(
                        orderStateEvent.nameBook
                    ).collect {
                        _orderResponse.value = it
                    }
                }
            }
        }
    }}