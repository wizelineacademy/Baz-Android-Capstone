package com.example.readbitso.repository.datastore



interface DataStoreRepository {

    suspend fun selectCoin(key: String, value: String)
    suspend fun putFlag(key: String, value: String)
    suspend fun getCoin(key: String):String?
    suspend fun getFlag(key: String): String?
    suspend fun setPage(key: String, value: String)
    suspend fun getPage(key: String):String?

}

