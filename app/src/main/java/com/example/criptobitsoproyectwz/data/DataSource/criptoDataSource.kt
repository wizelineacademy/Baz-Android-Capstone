package com.example.criptobitsoproyectwz.data.DataSource

import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import com.example.criptobitsoproyectwz.data.network.BitsoService
import com.example.criptobitsoproyectwz.data.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface criptoDataSource {
    suspend fun getAllCriptos(): Response<BaseResult>
}