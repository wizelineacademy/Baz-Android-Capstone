package com.javg.cryptocurrencies.data.repository

import com.javg.cryptocurrencies.data.db.dao.CRYTickerDao
import com.javg.cryptocurrencies.data.db.entity.CRYDetailBookEntity
import com.javg.cryptocurrencies.data.mapper.toDomain
import com.javg.cryptocurrencies.data.mapper.toEntity
import com.javg.cryptocurrencies.data.model.CRYBaseResponse
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.data.model.CRYTicker
import com.javg.cryptocurrencies.data.network.CRYApi
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 *
 * Allows you to retrieve business information from the specified book.
 *
 * @param cryApi is an interface that contains the remote query endpoints
 *
 * @since 2.0
 */
class CRYTickerRepository @Inject constructor(
    private val cryApi: CRYApi,
    private val tickerDao: CRYTickerDao,): CRYGenericRepository(){

    /**
     *
     */
    suspend fun getTicker(book: String): CRYDetailBook {
        var ticker = tickerDao.findById(book)

        if (ticker == null){
            println("Entra a consultar el api")
            val tickerAux = getTickerFromApi(book)
            tickerAux?.let {
                tickerDao.insert(it)
            }
            ticker = tickerDao.findById(book)
        }
        return ticker.toDomain()
    }

    private suspend fun getTickerFromApi(book: String): CRYDetailBookEntity? {
        var response = CRYBaseResponse<CRYTicker>()
        getResponse {  response = cryApi.getTicker(book) }
        return response.payload?.toEntity()
    }
}