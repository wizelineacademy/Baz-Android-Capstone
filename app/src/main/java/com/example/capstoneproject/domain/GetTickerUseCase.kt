package com.example.capstoneproject.domain

import com.example.capstoneproject.data.BitsoRepository
import com.example.capstoneproject.data.model.ticker.TickerModel

class GetTickerUseCase {
    private val repository = BitsoRepository()
    suspend operator fun invoke(book:String): TickerModel = repository.getTicker(book)
}