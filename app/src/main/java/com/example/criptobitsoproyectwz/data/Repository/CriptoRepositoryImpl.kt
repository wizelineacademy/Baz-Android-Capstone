package com.example.criptobitsoproyectwz.data.Repository

import com.example.criptobitsoproyectwz.data.DataSource.criptoDataSource
import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import retrofit2.Response
import javax.inject.Inject

class CriptoRepositoryImpl @Inject constructor(private val remoteDataSource: criptoDataSource): CriptoRepository {

    override suspend fun getAllCriptos(): Response<BaseResult> = remoteDataSource.getAllCriptos()

}