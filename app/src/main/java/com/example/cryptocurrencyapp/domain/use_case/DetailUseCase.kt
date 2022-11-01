package com.example.cryptocurrencyapp.domain.use_case

import com.example.cryptocurrencyapp.data.repository.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.utils.CryptoConstants
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


class DetailUseCase(private val repository: WCCryptoRepositoryImp){
    suspend fun ticker(book: String) : Flow<Resource<WCCTickerDTO>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getTickerBook(book)
            emit(Resource.Success(response))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: CryptoConstants.ERROR))
        }
    }
    suspend fun order(book: String): Flow<Resource<WCCOrdeRDTO>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getOrderBook(book)
            emit(Resource.Success(response))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: CryptoConstants.ERROR))
        }
    }
}
