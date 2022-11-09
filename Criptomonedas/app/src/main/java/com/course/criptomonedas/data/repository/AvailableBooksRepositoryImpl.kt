package com.course.criptomonedas.data.repository

import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSource
import com.course.criptomonedas.data.models.AvailableBooks

class AvailableBooksRepositoryImpl(
    private val dataSource: RemoteDataSource
) : AvailableBooksRepository {

    override suspend fun getAvailableBooks(): AvailableBooks = dataSource.getAvailableBooks()
}