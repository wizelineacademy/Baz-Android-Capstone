package com.lefg095.criptoone.data

import com.lefg095.criptoone.data.interfaces.IBookRepository
import com.lefg095.criptoone.domain.response.BaseResponse
import com.lefg095.criptoone.domain.stateevent.DataState
import com.lefg095.criptoone.di.ApiService
import com.lefg095.criptoone.domain.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository(
    private val apiService: ApiService
) : IBookRepository {

    override fun getBooks(
    ): Flow<DataState<BaseResponse<Book>>> = flow {
        emit(DataState.Loading("Cargando elementos..."))
        try{
            val response = apiService.getBooks()
            emit(DataState.Success(response))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

}