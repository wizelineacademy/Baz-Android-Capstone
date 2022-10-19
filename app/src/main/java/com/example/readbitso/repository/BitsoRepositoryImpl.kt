package com.example.readbitso.repository

import com.example.readbitso.datasource.BitsoDataSource
import com.example.readbitso.datasource.room.dao.AskBidsDao
import com.example.readbitso.datasource.room.dao.CriptocurrenciesDao
import com.example.readbitso.datasource.room.dao.TradesDao
import com.example.readbitso.datasource.room.dao.entity.AskBids
import com.example.readbitso.datasource.room.dao.entity.Currencies
import com.example.readbitso.datasource.room.dao.entity.OperationsTrades
import com.example.readbitso.models.bitsoModels.bitsoBooks.Books
import com.example.readbitso.models.bitsoModels.bitsoBooks.BooksPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.Tickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.PayloadTrades
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.Trades
import com.example.readbitso.repository.datastore.DataStoreRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class BitsoRepositoryImpl
@Inject
constructor(
    private val retrofitInstance: BitsoDataSource,
    private val currencyDao: CriptocurrenciesDao,
    private val tradingDao: TradesDao,
    private val askAndBidsDao: AskBidsDao,
    private val dataStoreRepository: DataStoreRepository,
) : BitsoRepository {
    override fun getRetrofitSingleBooks(): Single<Books> = retrofitInstance.getBooks()

    override suspend fun getRetrofitResponseAskBids(ticker: String): Flow<Response<Tickers>> =
        flow {
            emit(retrofitInstance.getTickerResponse(ticker))
            throw Exception()
        }

    override suspend fun getRetrofitResponseTrades(ticker: String): Flow<Response<Trades>> =
        flow {
            emit(retrofitInstance.getTradesResponse(ticker))
            throw RuntimeException()
        }

    override suspend fun insertDbBooks(book: List<BooksPayload>) {
        val ret = mutableListOf<Currencies>()
        book.map {
            Currencies(
                uid = it.id,
                book = it.book,
                maximumPrice = it.maximumPrice,
                minimumPrice = it.minimumPrice
            )
        }
            .forEach { ret.add(it) }

        currencyDao.insertAll(ret)
    }

    override suspend fun getDbBooks(): Flow<List<Currencies>> =
        flow { emit(currencyDao.getAll()) }

    override suspend fun insertDbTrades(trades: List<PayloadTrades>) {
        val ret = mutableListOf<OperationsTrades>()

        trades.forEachIndexed { index, it ->
            ret.add(OperationsTrades(
                uid = index,
                pair = it.book,
                amount = it.amount,
                type = it.makerSide,
                price = it.price))
        }
        tradingDao.insertAll(ret)
    }

    override suspend fun getDbTrades(): Flow<List<OperationsTrades>> =
        flow { emit(tradingDao.getAll()) }

    override suspend fun insertDbAsk(openedPayloadsCoin: List<PayloadTickers>) {
        val ret = mutableListOf<AskBids>()
        openedPayloadsCoin
            .map {
                AskBids(
                    uid = 0,
                    ask = it.ask,
                    bid = it.bid,
                    book = it.book
                )
            }
            .forEach { ret.add(it) }

        askAndBidsDao.insertAll(ret)
    }

    override suspend fun getDbAskBids(): Flow<List<AskBids>> =
        flow { emit(askAndBidsDao.getAll()) }

    override suspend fun selectCurrency(key1: String, key2: String) {
        dataStoreRepository.selectCoin(key1, key2)
    }

    override suspend fun getSelectedCurrency(key: String): String? {
        var coin: String? = ""
        dataStoreRepository.getCoin(key)
            .catch { }
            .collect { coin = it }
        return coin
    }

    override suspend fun setActualView(key: String, value: String) {
        dataStoreRepository.setPage(key, value)
    }

    override suspend fun getActualView(key: String): String? {
        var page: String? = ""
        dataStoreRepository.getCoin(key)
            .catch { }
            .collect {
                page = it
            }
        return page
    }

    override suspend fun getInternetErrorResponse(error: Throwable): List<BooksPayload> {

        when (error) {
            is UnknownHostException -> setInternetError("wifi", "Datos no disponibles")
        }
        return getErrorBooks()
    }

    private suspend fun getErrorBooks(): MutableList<BooksPayload> {
        val list = mutableListOf<BooksPayload>()
        getDbBooks()
            .collect { it ->
                it.forEach {
                    list.add(BooksPayload(
                        id = it.uid,
                        book = it.book,
                        maximumPrice = it.maximumPrice,
                        minimumPrice = it.minimumPrice,
                    ))
                }
            }
        return list
    }

    override suspend fun setInternetError(key: String, value: String) {
        dataStoreRepository.setInternetFlag(key, value)
    }

    override suspend fun getInternetError(key: String): String? {
        var internetFlag: String? = ""
        dataStoreRepository.getInternetFlag(key)
            .catch { }
            .collect {
                internetFlag = it
            }
        return internetFlag
    }

    override suspend fun getRetrofitBooksResponse() : Flow<Response<Books>> =
        flow {
                emit(retrofitInstance.getBooksResponse())
                throw Exception()
               }
}
