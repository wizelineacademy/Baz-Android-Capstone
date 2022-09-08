package com.example.capproject.repository

import com.example.capproject.interfaces.BinanceDataSource
import javax.inject.Inject

interface BinanceRepository {
      suspend fun getbooks():List<com.example.capproject.models.Book.Payload>
      suspend fun getinfocoin(coin: String):List<com.example.capproject.models.Tickers.Payload>
}

class BinanceRepositoryImp
@Inject constructor(private val data:BinanceDataSource) :BinanceRepository
{
    override suspend fun getbooks(): List<com.example.capproject.models.Book.Payload> =
        data.getBooks().payload
    override suspend fun getinfocoin(coin:String): List<com.example.capproject.models.Tickers.Payload> =
        listOf(data.specificTicker(coin).payload)
}