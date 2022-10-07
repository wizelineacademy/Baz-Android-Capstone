package com.example.cryptocurrency_challenge.domain

import com.example.cryptocurrency_challenge.data.model.Available_books_response
import com.example.cryptocurrency_challenge.repository.AvailableBooksRepository
import javax.inject.Inject

class AvailableBooksUseCase @Inject constructor (private val repository: AvailableBooksRepository) {
    suspend operator fun invoke() : Available_books_response =
        repository.getAvailable_books()
}