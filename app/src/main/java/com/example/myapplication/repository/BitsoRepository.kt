package com.example.myapplication.repository

import com.example.myapplication.data.model.BookResponse

interface BitsoRepository {

    suspend fun getAvailableBooks(): BookResponse
}