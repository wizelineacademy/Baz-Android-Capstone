package com.example.readbitso.repository

import com.example.readbitso.ds.BitsoDataSource
import com.example.readbitso.models.bitsoBooks.Books
import com.example.readbitso.models.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsotickers.Tickers
import com.example.readbitso.models.trading.PayloadTrades
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BitsoRepositoryImp
@Inject constructor(private val retro: BitsoDataSource) : BitsoRepository {
    override fun getBitsoBooks(): Observable<Books> = retro.getBooks()

    override suspend fun getBitsoBids(ticker: String): Flow<List<PayloadTickers>> =
        flow { emit(listOf(retro.specificTicker(ticker).payload)) }

    override suspend fun getBitsoTrades(ticker: String): Flow<List<PayloadTrades>> =
        flow { emit(retro.specificTrade(ticker).payload) }



}