package com.example.readbitso.repository

import com.example.readbitso.ds.BitsoDataSource
import com.example.readbitso.ds.room.dao.AskBidsDao
import com.example.readbitso.ds.room.dao.CriptocurrenciesDao
import com.example.readbitso.ds.room.dao.TradesDao
import com.example.readbitso.ds.room.dao.entity.AskBids
import com.example.readbitso.ds.room.dao.entity.Currencies
import com.example.readbitso.ds.room.dao.entity.Operationstrades
import com.example.readbitso.models.bitsoModels.bitsoBooks.Books
import com.example.readbitso.models.bitsoModels.bitsoBooks.BooksPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.Tickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.PayloadTrades
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.Trades
import com.example.readbitso.repository.datastore.DataStoreRepository
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class BitsoRepositoryImp
@Inject
constructor(
    private val retro: BitsoDataSource,
    private val db1: CriptocurrenciesDao,
    private val db2: TradesDao,
    private val db3: AskBidsDao,
    private val dsRepository: DataStoreRepository,
) : BitsoRepository {
    override fun getRfBitsoBooks(): Observable<Books> = retro.getBooks()

    override suspend fun getRfBitsoBids(ticker: String): Flow<Response<Tickers>> =
        flow {
            emit(retro.specificTicker(ticker))
            throw Exception()
        }

    override suspend fun getRfBitsoTrades(ticker: String): Flow<Response<Trades>> =
        flow {
            emit(retro.specificTrade(ticker))
            throw RuntimeException()
        }

    override suspend fun insertDbBooks(book: List<BooksPayload>) {
        val ret = mutableListOf<Currencies>()
        book.map {
            Currencies(
                id = it.id,
                book = it.book,
                maximum_price = it.maximum_price,
                minimum_price = it.minimum_price
            )
        }
            .forEach { ret.add(it) }

        db1.insertAll(ret)
    }

    override suspend fun getDbBooks(): Flow<List<Currencies>> =
        flow { emit(db1.getAll()) }

    override suspend fun insertDbTrades(trades: List<PayloadTrades>) {
        val ret = mutableListOf<Operationstrades>()

        trades.forEachIndexed { index, it ->
            ret.add(Operationstrades(
                id = index,
                pair = it.book,
                Amount = it.amount,
                Type = it.maker_side,
                Price = it.price))
        }
        db2.insertAll(ret)
    }

    override suspend fun getDbTrades(): Flow<List<Operationstrades>> =
        flow { emit(db2.getAll()) }

    override suspend fun insertDbAsk(openedPayloadsCoin: List<PayloadTickers>) {
        val ret = mutableListOf<AskBids>()
        openedPayloadsCoin
            .map {
                AskBids(
                    id = 0,
                    Ask = it.ask,
                    Bid = it.bid,
                    Book = it.book
                )
            }
            .forEach { ret.add(it) }

        db3.insertAll(ret)
    }

    override suspend fun getDbAskBids(): Flow<List<AskBids>> =
        flow { emit(db3.getAll()) }

    override suspend fun selectActualToken(key1: String, key2: String) {
        dsRepository.selectCoin(key1, key2)
    }

    override suspend fun getActualToken(key: String): String? {
        var coin: String? = ""
        dsRepository.getCoin(key)
            .catch { }
            .collect { coin = it }
        return coin
    }

    override suspend fun setActualView(key: String, value: String) {
        dsRepository.setPage(key, value)
    }

    override suspend fun getActualView(key: String): String? {
        var page: String? = ""
        dsRepository.getCoin(key)
            .catch { }
            .collect {
                page = it
            }
        return page
    }

    override suspend fun internetError(error: Throwable): List<BooksPayload> {

        when (error) {
            is UnknownHostException -> setInternetFlag("wifi", "Datos no disponibles")
        }
        return getErrorBooks()
    }

    private suspend fun getErrorBooks(): MutableList<BooksPayload> {
        val list = mutableListOf<BooksPayload>()
        getDbBooks()
            .collect {
                it.forEach {
                    list.add(BooksPayload(
                        id = it.id,
                        book = it.book,
                        maximum_price = it.maximum_price,
                        minimum_price = it.minimum_price,
                    ))
                }
            }
        return list
    }

    override suspend fun setInternetFlag(key: String, value: String) {
        dsRepository.setInternetFlag(key, value)
    }

    override suspend fun getInternetFlag(key: String): String? {
        var internetFlag: String? = ""
        dsRepository.getInternetFlag(key)
            .catch { }
            .collect {
                internetFlag = it
            }
        return internetFlag
    }

    override suspend fun getretrofitresponse() : Flow<Response<Books>> =
        flow {
                emit(retro.getBooks1())
                throw Exception()
               }

}
