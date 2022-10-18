package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.Payload_Ticker
import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor (private val remoteDataSource : NetworkDataSource) :
    TickerRepository {
        override suspend fun get_Ticker(currency_name: String?): Payload_Ticker {
            return if (remoteDataSource.getTicker(currency_name).body()!!.success){
                remoteDataSource.getTicker(currency_name).body()!!.payload
            }else{
                Payload_Ticker()
            }
        }
    }



