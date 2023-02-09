package com.javg.cryptocurrencies.data.repository

import com.javg.cryptocurrencies.data.model.CRYBaseResponse
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
class CRYTickerRepository @Inject constructor(private val cryApi: CRYApi): CRYGenericRepository(){

    /**
     *
     */
    suspend fun getTicker(book: String): CRYBaseResponse<CRYTicker>{
        var response = CRYBaseResponse<CRYTicker>()
        getResponse {  response = cryApi.getTicker(book) }
        return response
    }
}