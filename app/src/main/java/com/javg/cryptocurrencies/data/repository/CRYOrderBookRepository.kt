package com.javg.cryptocurrencies.data.repository

import com.javg.cryptocurrencies.data.db.dao.CRYTickerDao
import com.javg.cryptocurrencies.data.mapper.toDomainAll
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.data.network.CRYApi
import com.javg.cryptocurrencies.utils.CRYUtils
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 * Date modified 13/02/2023
 *
 * allows you to retrieve a list of all open orders in
 * the specified book.
 *
 * @param cryApi is an interface that contains the remote query endpoints
 *
 * @since 2.0
 */
class CRYOrderBookRepository @Inject constructor(private val cryApi: CRYApi,
                                                 private val tickerDao: CRYTickerDao) {

    /**
     * Returns a book detail model obtaining the information from the database or,
     * if it is empty, consult it with the remote api
     *
     * @param book is the name of the book to consult its specific information
     */
    suspend fun getOrderBook(book: String): CRYDetailBook {
        var ticker = tickerDao.findById(book)

        ticker?.let { detailBookEntity ->
            if (detailBookEntity.askList.isEmpty() || detailBookEntity.bidsList.isEmpty()){
                val response = getOrderBookFromApi(book)
                response.payload?.let {
                    tickerDao.update(
                        ask = CRYUtils.convertersListToJson(it.asksList),
                        bids = CRYUtils.convertersListToJson(it.bidsList),
                        book = book)
                }
                ticker = tickerDao.findById(book)
            }
        }

        return ticker?.toDomainAll() ?: CRYDetailBook()
    }

    /**
     * Returns a book order type model consulting the information remotely to an api
     *
     * @param book is the name of the book to consult its specific information
     */
    private suspend fun getOrderBookFromApi(book: String) = cryApi.getOrderBook(book)
}