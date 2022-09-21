package com.example.capstoneproject.domain

import com.example.capstoneproject.data.BitsoRepository
import com.example.capstoneproject.data.model.availableBooks.AvailableBookModel

class GetAvailableBooksUseCase {
    private val repository = BitsoRepository()
    suspend operator fun invoke(): List<AvailableBookModel> = repository.getAllAvailableBooks()
}