package com.carteagal.baz_android.domain.useCase

import com.carteagal.baz_android.data.model.orderBook.OrderBookResponse
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.data.repository.OrderBooksRepository
import javax.inject.Inject

class GetOrderBookUseCase @Inject constructor(
    private val orderBooksRepository: OrderBooksRepository
) {
    suspend operator fun invoke(data: String): Resources<List<OrderBookResponse>>{
        return try {
            Resources.Success(orderBooksRepository.getOrderBooks(data))
        }catch (e: Exception){
            Resources.Error(e.message)
        }
    }
}