package com.example.capproject.repository

import com.example.capproject.interfaces.BitsoDataSource
import com.example.capproject.models.Book.Books
import com.example.capproject.models.Book.Payload
import com.example.capproject.models.trading.PayloadTrades
import com.example.capproject.models.trading.Trades
import com.example.capproject.room.CriptocurrenciesDao
import com.example.capproject.room.Currencies
import com.example.capproject.room.Operationstrades
import com.example.capproject.room.TradesDao
import kotlinx.coroutines.*
import javax.inject.Inject

interface BitsoRepository {
    suspend fun getinfocoin(coin: String):List<com.example.capproject.models.Tickers.Payload>
    suspend fun tradesinfo(coin: String):List<PayloadTrades>
    suspend fun insert(list: Set<Payload>)
    suspend fun tradesinfo2(coin: String): Trades
    suspend fun getbooks1(): Books
    suspend fun inserttrades(list: List<PayloadTrades>)
}

class BitsoRepositoryImp
@Inject constructor(private val data:BitsoDataSource,
                    private val db:CriptocurrenciesDao,
                    private val bd1:TradesDao) :BitsoRepository {

    private val listamutable = mutableListOf<Currencies>()

    override suspend fun getbooks1(): Books = data.getBooks()
    override suspend fun inserttrades(list: List<PayloadTrades>)
    {
        val listamutable = mutableListOf<Operationstrades>()

        list.forEachIndexed {index,item ->
            listamutable.add(
                Operationstrades(uid = index,
                    Amount = item.amount
                    ,Type=item.maker_side,
                    Price = item.price,
                    pair = item.book
                ))
        }

        withContext(Dispatchers.IO){
            bd1.insertAll(listamutable)
        }
    }

    override suspend fun insert(list: Set<Payload>) {

        list.forEachIndexed { index, it ->
            listamutable.add(Currencies(
                uid = index,
                Name = it.book,
                minvalue = it.minimum_price,
                maxvalue = it.maximum_price,
            )
            )
        }
        withContext(Dispatchers.IO) {
            db.insertAll(listamutable)
        }


    }

    override suspend fun tradesinfo2(coin: String): Trades = data.specificTrade(coin)


    override suspend fun getinfocoin(coin: String): List<com.example.capproject.models.Tickers.Payload> =
        listOf(data.specificTicker(coin).payload)

    override suspend fun tradesinfo(coin: String): List<PayloadTrades> =
        data.specificTrade(coin).payload
}
