package com.example.readbitso.repository.datastore

import kotlinx.coroutines.flow.Flow


interface DataStoreRepository {

    suspend fun selectCoin(key: String, value: String)
    suspend fun putFlag(key: String, value: String)
    suspend fun getCoin(key: String): Flow<String?>
    suspend fun getFlag(key: String): String?
    suspend fun setPage(key: String, value: String)
    suspend fun getPage(key: String):Flow<String?>

}

