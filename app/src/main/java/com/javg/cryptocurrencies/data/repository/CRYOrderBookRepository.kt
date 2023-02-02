package com.javg.cryptocurrencies.data.repository

import com.javg.cryptocurrencies.data.network.CRYApi
import javax.inject.Inject

class CRYOrderBookRepository @Inject constructor(private val cryApi: CRYApi) {

    suspend fun getOrderBook(book: String) = cryApi.getOrderBook(book)
}