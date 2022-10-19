package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.PayloadTicker
import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor (private val remoteDataSource : NetworkDataSource) :
    TickerRepository {
        override suspend fun getTicker(currency_name: String?): PayloadTicker {
            val result = remoteDataSource.getTicker(currency_name).body()
            return result?.payload ?: PayloadTicker()
        }
    }



