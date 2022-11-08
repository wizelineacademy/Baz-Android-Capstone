package com.example.myapplication.repository

import com.example.myapplication.data.model.BookResponse
import com.example.myapplication.data.remote.BitsoDataSource

class BitsoRepositoryImpl(private  val dataSource: BitsoDataSource): BitsoRepository {

    override suspend fun getAvailableBooks(): BookResponse = dataSource.getAvailableBooks()

}