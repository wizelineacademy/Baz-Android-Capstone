package com.example.capproject.repository

import com.example.capproject.interfaces.BinanceDataSource
import com.example.capproject.models.book.Payload
import javax.inject.Inject

interface BinanceRepository {
      suspend fun getbooks():List<Payload>
}

class BinanceRepositoryImp
@Inject constructor(private val data:BinanceDataSource,) :BinanceRepository
{
    override suspend fun getbooks(): List<Payload> = data.getBooks().payload
}