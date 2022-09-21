package com.example.capstoneproject.domain

import com.example.capstoneproject.data.BitsoRepository
import com.example.capstoneproject.data.model.orderBook.OrderBookModel

class GetOrderBookUseCase {
    private val repository = BitsoRepository()
    suspend operator fun invoke(book:String): OrderBookModel = repository.getOrderBook(book)
}