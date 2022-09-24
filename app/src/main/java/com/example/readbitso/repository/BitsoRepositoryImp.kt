package com.example.readbitso.repository

import com.example.readbitso.ds.BitsoDataSource
import com.example.readbitso.ds.room.dao.CriptocurrenciesDao
import com.example.readbitso.ds.room.dao.entity.Currencies
import com.example.readbitso.models.bitsoModels.bitsoBooks.Books
import com.example.readbitso.models.bitsoModels.bitsoBooks.BooksPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.PayloadTrades
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BitsoRepositoryImp
@Inject constructor(private val retro: BitsoDataSource,
private val db1:CriptocurrenciesDao) : BitsoRepository {
    override fun getBitsoBooks(): Observable<Books> = retro.getBooks()

    override suspend fun getBitsoBids(ticker: String): Flow<List<PayloadTickers>> =
        flow { emit(listOf(retro.specificTicker(ticker).payload)) }

    override suspend fun getBitsoTrades(ticker: String): Flow<List<PayloadTrades>> =
        flow { emit(retro.specificTrade(ticker).payload) }

    override suspend fun insertBooks(book: List<BooksPayload>)
    {
        val ret = mutableListOf<Currencies>()
        book.forEach {
            ret.add(Currencies(id=it.id,
                book=it.book,
                maximum_price = it.maximum_price,
                minimum_price=it.minimum_price))
        }
        db1.insertAll(ret)
    }

    override suspend fun getflowBooks(): Flow<List<Currencies>> =
        flow <List<Currencies>>
        {
            emit(db1.getAll())
        }



}