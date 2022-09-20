package com.example.capproject.datastore



interface DataStoreRepository {

    suspend fun selectCoin(key: String, value: String)
    suspend fun putFlag(key: String, value: String)
    suspend fun getCoin(key: String):String?
    suspend fun getFlag(key: String): String?
    suspend fun setNetworkStatus(key: String, value: String)
    suspend fun getNetworkStatus(key: String):String?
}

