package com.course.criptomonedas.data.repository

import com.course.criptomonedas.data.models.AvailableBooks

interface AvailableBooksRepository {

    suspend fun getAvailableBooks(): AvailableBooks
}