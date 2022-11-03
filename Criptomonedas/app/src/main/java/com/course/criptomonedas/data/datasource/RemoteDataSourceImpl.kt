package com.course.criptomonedas.data.datasource

import com.course.criptomonedas.data.models.AvailableBooks
import com.course.criptomonedas.data.network.AvailableBooksService

class RemoteDataSourceImpl(
    private val service: AvailableBooksService
) : RemoteDataSource {
    override suspend fun getAvailableBooks(): AvailableBooks {
        return service.getBooks()
    }
}