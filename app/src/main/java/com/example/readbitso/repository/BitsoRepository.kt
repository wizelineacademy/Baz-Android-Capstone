package com.example.readbitso.repository

import com.example.readbitso.datasource.room.dao.entity.AskBids
import com.example.readbitso.datasource.room.dao.entity.Currencies
import com.example.readbitso.datasource.room.dao.entity.OperationsTrades
import com.example.readbitso.models.bitsoModels.bitsoBooks.Books
import com.example.readbitso.models.bitsoModels.bitsoBooks.BooksPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.Tickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.PayloadTrades
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.Trades
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BitsoRepository {
    fun getRetrofitSingleBooks(): Single<Books> // rxjava
    suspend fun getRetrofitResponseAskBids(ticker: String): Flow<Response<Tickers>>
    suspend fun getRetrofitResponseTrades(ticker: String): Flow<Response<Trades>>
    suspend fun insertDbBooks(book: List<BooksPayload>)
    suspend fun insertDbTrades(trades: List<PayloadTrades>)
    suspend fun insertDbAsk(openedPayloadsCoin: List<PayloadTickers>)
    suspend fun getDbBooks(): Flow<List<Currencies>>
    suspend fun getDbTrades(): Flow<List<OperationsTrades>>
    suspend fun getDbAskBids(): Flow<List<AskBids>>
    suspend fun selectCurrency(key1: String, key2: String)
    suspend fun getSelectedCurrency(key: String): String?
    suspend fun setActualView(key: String, value: String)
    suspend fun getActualView(key: String): String?
    suspend fun getInternetErrorResponse(error: Throwable): List<BooksPayload>
    suspend fun setInternetError(key: String, value: String)
    suspend fun getInternetError(key: String): String?
    suspend fun getRetrofitBooksResponse(): Flow<Response<Books>>
}
