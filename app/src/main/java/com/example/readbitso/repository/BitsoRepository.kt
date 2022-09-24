package com.example.readbitso.repository


import com.example.readbitso.ds.room.dao.entity.Currencies
import com.example.readbitso.models.bitsoModels.bitsoBooks.Books
import com.example.readbitso.models.bitsoModels.bitsoBooks.BooksPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.PayloadTrades
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

interface BitsoRepository {
    fun getBitsoBooks(): Observable<Books>//rxjava
    suspend fun getBitsoBids(ticker:String):Flow<List<PayloadTickers>>
    suspend fun getBitsoTrades(ticker: String): Flow<List<PayloadTrades>>
    suspend fun insertBooks(book:List<BooksPayload>)
    suspend fun getflowBooks():Flow<List<Currencies>>


}

