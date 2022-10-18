package com.example.cryptocurrency_challenge.domain

import com.example.cryptocurrency_challenge.data.model.Payload
import com.example.cryptocurrency_challenge.repository.AvailableBooksRepository
import javax.inject.Inject

class AvailableBooksUseCase @Inject constructor (private val repository: AvailableBooksRepository) {
    suspend operator fun invoke() : List<Payload> =
        repository.getAvailable_books()
}