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
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.PayloadTrades
import com.example.readbitso.repository.datastore.DataStoreRepository
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BitsoRepositoryImp
@Inject
constructor(private val retro: BitsoDataSource,
            private val db1:CriptocurrenciesDao,
            private val db2: TradesDao,
            private val db3: AskBidsDao,
            private val dsRepository: DataStoreRepository
) : BitsoRepository
{
    override fun getBitsoBooks(): Observable<Books> = retro.getBooks()

    override suspend fun getBitsoBids(ticker: String): Flow<List<PayloadTickers>> =
        flow { emit(listOf(retro.specificTicker(ticker).payload)) }

    override suspend fun getBitsoTrades(ticker: String): Flow<List<PayloadTrades>> =
        flow { emit(retro.specificTrade(ticker).payload) }

    override suspend fun insertBooks(book: List<BooksPayload>) {
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
        flow  { emit(db1.getAll()) }

    override suspend fun insertTrades(trades: List<PayloadTrades>) {
        val ret = mutableListOf<Operationstrades>()
        trades.forEachIndexed {index,it->
            ret.add(Operationstrades(id=index,
                pair=it.book,
                Amount = it.amount,
                Type=it.maker_side,
                Price=it.price))
        }
        db2.insertAll(ret)
    }

    override suspend fun getflowTrades(): Flow<List<Operationstrades>> =
        flow{ emit(db2.getAll()) }

    override suspend fun insertAsk(openedPayloadsCoin: List<PayloadTickers>) {
        val ret = mutableListOf<AskBids>()
        openedPayloadsCoin.forEachIndexed{ index, it->
            ret.add(AskBids(
                id = index,
                Ask=it.ask,
                Bid=it.bid,
                Book=it.book
            ))
        }
        db3.insertAll(ret)
    }

    override suspend fun getflowAskBids(): Flow<List<AskBids>> =
        flow{ emit(db3.getAll()) }

    override suspend fun selectCoin(key1: String, key2: String) {
        dsRepository.selectCoin(key1, key2)
    }

    override suspend fun getCoin(key: String): String? {
        var coin:String?=""
        dsRepository.getCoin(key)
            .catch {  }
            .collect{
                coin = it
            }
        return coin
    }

    override suspend fun setPage(key: String, value: String) {
        dsRepository.setPage(key,value)
    }

    override suspend fun getPage(key: String): String? {
        var page:String?=""
        dsRepository.getCoin(key)
            .catch {  }
            .collect{
                page = it
            }
        return page
    }


}