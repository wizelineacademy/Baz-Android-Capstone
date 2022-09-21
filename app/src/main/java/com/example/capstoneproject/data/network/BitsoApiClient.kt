package com.example.capstoneproject.data.network

import com.example.capstoneproject.core.RetrofitHelper
import com.example.capstoneproject.data.model.availableBooks.AvailableBookModel
import com.example.capstoneproject.data.model.BitsoProvider
import com.example.capstoneproject.data.model.orderBook.OrderBookModel
import com.example.capstoneproject.data.model.ticker.TickerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BitsoApiClient {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAvailableBooks():List<AvailableBookModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(BitsoService::class.java).getAllAvailableBooks()
            response.body()?.payload ?: emptyList()
        }
    }

    suspend fun getTicker(book:String): TickerModel{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(BitsoService::class.java).getTicker(book)
            response.body()?.payload ?: TickerModel()
        }
    }

    suspend fun getOrderBook(book:String): OrderBookModel{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(BitsoService::class.java).getOrderBook(book)
            response.body()?.payload ?: OrderBookModel()
        }
    }
}