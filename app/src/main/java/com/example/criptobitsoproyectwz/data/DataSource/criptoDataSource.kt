package com.example.criptobitsoproyectwz.data.DataSource

import com.example.criptobitsoproyectwz.data.network.ApiServiceBitsoInterface
import com.example.criptobitsoproyectwz.data.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class criptoDataSource {
    suspend fun getCriptos(){
        return withContext(Dispatchers.IO){
            val response = Retrofit.getRetrofit().create(ApiServiceBitsoInterface::class.java).getCriptos("available_books")
            response.body()
        }
    }
}