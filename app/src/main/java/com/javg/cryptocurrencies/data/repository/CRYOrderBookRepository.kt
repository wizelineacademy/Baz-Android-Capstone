package com.javg.cryptocurrencies.data.repository

import com.javg.cryptocurrencies.data.network.CRYApi
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 *
 * allows you to retrieve a list of all open orders in
 * the specified book.
 *
 * @param cryApi is an interface that contains the remote query endpoints
 *
 * @since 2.0
 */
class CRYOrderBookRepository @Inject constructor(private val cryApi: CRYApi) {

    /**
     *
     */
    suspend fun getOrderBook(book: String) = cryApi.getOrderBook(book)
}