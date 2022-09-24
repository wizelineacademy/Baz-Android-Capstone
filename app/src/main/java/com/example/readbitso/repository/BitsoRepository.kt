package com.example.readbitso.repository


import com.example.readbitso.models.bitsoBooks.Books
import com.example.readbitso.models.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsotickers.Tickers
import com.example.readbitso.models.trading.PayloadTrades
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

interface BitsoRepository {
    fun getBitsoBooks(): Observable<Books>//rxjava
    suspend fun getBitsoBids(ticker:String):Flow<List<PayloadTickers>>
    suspend fun getBitsoTrades(ticker: String): Flow<List<PayloadTrades>>


}

