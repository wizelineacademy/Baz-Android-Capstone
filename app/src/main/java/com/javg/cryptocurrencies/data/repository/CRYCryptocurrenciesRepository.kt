package com.javg.cryptocurrencies.data.repository

import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.network.CRYApi
import javax.inject.Inject

class CRYCryptocurrenciesRepository  @Inject constructor(private val cryApi: CRYApi){

    suspend fun getListAvailableBooks(): List<CRYBook>{
        return cryApi.getListAvailableBooks().payload
    }
}