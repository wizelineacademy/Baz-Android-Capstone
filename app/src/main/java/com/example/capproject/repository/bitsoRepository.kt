package com.example.capproject.repository

import com.example.capproject.interfaces.BitsoDataSource
import com.example.capproject.models.Book.Books
import com.example.capproject.models.Book.Payload
import com.example.capproject.models.trading.PayloadTrades
import javax.inject.Inject

interface BitsoRepository {
    suspend fun getbooks():List<Payload>
    suspend fun getinfocoin(coin: String):List<com.example.capproject.models.Tickers.Payload>
    suspend fun tradesinfo(coin: String):List<PayloadTrades>
    suspend fun getresponse(): Books
}

class BitsoRepositoryImp
@Inject constructor(private val data:BitsoDataSource) :BitsoRepository
{
    override suspend fun getbooks(): List<Payload> =    data.getBooks().payload
    override suspend fun getresponse(): Books =    data.getBooks()
    override suspend fun getinfocoin(coin: String): List<com.example.capproject.models.Tickers.Payload> =
        listOf(data.specificTicker(coin).payload)
    override suspend fun tradesinfo(coin: String): List<PayloadTrades> = data.specificTrade(coin).payload
}