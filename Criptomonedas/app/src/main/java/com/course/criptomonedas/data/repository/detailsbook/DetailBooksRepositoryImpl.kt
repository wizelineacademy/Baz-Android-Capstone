package com.course.criptomonedas.data.repository.detailsbook

import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSource
import com.course.criptomonedas.data.models.ModelDetails

class DetailBooksRepositoryImpl(
    private val dataSource: RemoteDataSource
) : DetailBooksRepository {
    override suspend fun getDetailBooks(id: String): ModelDetails = dataSource.getDetailBooks(id)
}