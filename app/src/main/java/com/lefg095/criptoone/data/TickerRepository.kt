package com.lefg095.criptoone.data

import com.lefg095.criptoone.data.interfaces.ITickerRepository
import com.lefg095.criptoone.di.ApiService
import com.lefg095.criptoone.domain.stateevent.DataState
import com.lefg095.criptoone.domain.response.TickerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class TickerRepository(
    private val apiService: ApiService
) : ITickerRepository {

    override fun getTicker(
        nameBook: String
    ): Flow<DataState<TickerResponse>> = flow {
        emit(DataState.Loading("Cargando detalle..."))
        try {
            val response = apiService.getTicker(nameBook)
            emit(DataState.Success(response))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }

    }


}