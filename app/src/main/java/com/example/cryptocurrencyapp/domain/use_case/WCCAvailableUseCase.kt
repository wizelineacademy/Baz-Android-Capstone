package com.example.cryptocurrencyapp.domain.use_case

import com.example.cryptocurrencyapp.data.remote.data_source.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.utils.CryptoConstants
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

class WCCAvailableUseCase @Inject constructor(private val repository: WCCryptoRepositoryImp) {
    suspend fun coin(): Flow<Resource<List<WCCryptoBookDTO>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getAvailableBooks()
            emit(Resource.Success(response))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: CryptoConstants.ERROR))
        }
    }
}