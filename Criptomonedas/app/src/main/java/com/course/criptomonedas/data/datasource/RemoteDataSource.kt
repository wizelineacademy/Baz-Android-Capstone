package com.course.criptomonedas.data.datasource

import com.course.criptomonedas.data.models.AvailableBooks

interface RemoteDataSource {
    suspend fun getAvailableBooks(): AvailableBooks
}