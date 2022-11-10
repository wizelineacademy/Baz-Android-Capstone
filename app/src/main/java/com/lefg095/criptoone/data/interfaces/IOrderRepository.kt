package com.lefg095.criptoone.data.interfaces

import com.lefg095.criptoone.domain.Book
import com.lefg095.criptoone.domain.Order
import com.lefg095.criptoone.domain.response.BaseResponse
import com.lefg095.criptoone.domain.response.OrderResponse
import com.lefg095.criptoone.domain.stateevent.DataState
import kotlinx.coroutines.flow.Flow

interface IOrderRepository {

    fun getOrder(nameBook: String): Flow<DataState<OrderResponse>>

}
