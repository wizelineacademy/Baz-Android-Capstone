package com.javg.cryptocurrencies.data.repository

import com.javg.cryptocurrencies.data.model.CRYBaseResponse
import com.javg.cryptocurrencies.data.model.CRYBookResponse
import com.javg.cryptocurrencies.data.network.CRYApi
import javax.inject.Inject

class CRYBookRepository  @Inject constructor(private val cryApi: CRYApi){
    suspend fun getAvailableBooks(): CRYBaseResponse<List<CRYBookResponse>> = cryApi.getListAvailableBooks()
}