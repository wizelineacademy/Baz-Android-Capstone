package com.example.cryptocurrency_challenge.domain

import com.example.cryptocurrency_challenge.data.model.OrderBookResponse
import com.example.cryptocurrency_challenge.repository.OrderBookRepository
import javax.inject.Inject

class OrderBookUseCase @Inject constructor (private val repository: OrderBookRepository) {
    suspend operator fun invoke(currency_name: String?): OrderBookResponse =
        repository.get_OrderBook(currency_name)
}