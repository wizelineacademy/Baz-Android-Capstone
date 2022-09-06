package com.example.criptobitsoproyectwz.data.Repository

import com.example.criptobitsoproyectwz.data.network.ApiServiceBitso
import com.example.criptobitsoproyectwz.data.network.Retrofit
import com.example.criptobitsoproyectwz.data.model.BaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryCripto {
    /*** Repository
     * clase se encargaría de ir a base de datos(Room) o a internet.
     */
    suspend fun getAllCriptos():BaseResult?{
        return withContext(Dispatchers.IO){
            val response = Retrofit.getRetrofit().create(ApiServiceBitso::class.java).getCriptos("available_books")
            response.body()
        }

    }

}