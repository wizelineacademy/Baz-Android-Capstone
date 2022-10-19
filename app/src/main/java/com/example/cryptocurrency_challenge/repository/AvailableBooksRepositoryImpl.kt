package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.Payload
import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import javax.inject.Inject

class AvailableBooksRepositoryImpl @Inject constructor (private val remoteDataSource : NetworkDataSource) :
    AvailableBooksRepository {
    override suspend fun getAvailableBooks(): List<Payload> {
        val result = remoteDataSource.getAvailableBooksRX().body()
        return result?.payload?.filter { it.book.contains("mxn") } ?: emptyList()
    }
}