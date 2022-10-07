package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.Available_books_response
import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import javax.inject.Inject

class AvailableBooksRepositoryImpl @Inject constructor (private val remoteDataSource : NetworkDataSource) :
    AvailableBooksRepository {
        override suspend fun getAvailable_books() : Available_books_response =
            remoteDataSource.getAvailablebooks().body()!!
}