package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.Payload
import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import javax.inject.Inject

class AvailableBooksRepositoryImpl @Inject constructor (private val remoteDataSource : NetworkDataSource) :
    AvailableBooksRepository {
        override suspend fun getAvailable_books(): List<Payload> {
            return if (remoteDataSource.getAvailablebooksRX().body()!!.success){
                remoteDataSource.getAvailablebooksRX().body()!!.payload.filter{ it.book.contains("mxn") }
            }else{
                emptyList()
            }
        }
}