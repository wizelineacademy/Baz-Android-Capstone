package com.lefg095.criptoone.data

import com.lefg095.criptoone.data.interfaces.IOrderRepository
import com.lefg095.criptoone.di.ApiService
import com.lefg095.criptoone.domain.response.OrderResponse
import com.lefg095.criptoone.domain.stateevent.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class OrderRepository(
    private val apiService: ApiService
) : IOrderRepository {

    override fun getOrder(
        nameBook: String
    ): Flow<DataState<OrderResponse>> = flow{

        emit(DataState.Loading("Cargando ordenes..."))
        try {
            val response = apiService.getOrders(nameBook)
            emit(DataState.Success(response))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

}
