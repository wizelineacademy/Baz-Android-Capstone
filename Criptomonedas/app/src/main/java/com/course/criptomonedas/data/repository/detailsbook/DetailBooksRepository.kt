package com.course.criptomonedas.data.repository.detailsbook

import com.course.criptomonedas.data.models.ModelDetails

interface DetailBooksRepository {

    suspend fun getDetailBooks(id:String): ModelDetails
}