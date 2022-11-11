package com.course.criptomonedas.domain

import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.data.repository.detailsbook.DetailBooksRepository

class GetDetailBookCase(private val repository: DetailBooksRepository) {
    suspend operator fun invoke(id: String): ModelDetails = repository.getDetailBooks(id)
}