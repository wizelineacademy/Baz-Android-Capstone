package com.example.myapplication.domain

import com.example.myapplication.data.model.BookResponse
import com.example.myapplication.repository.BitsoRepository

class GetAvailableBookUseCase(private val bitsoRepository: BitsoRepository) {
    suspend fun invoke(): BookResponse = bitsoRepository.getAvailableBook()

}