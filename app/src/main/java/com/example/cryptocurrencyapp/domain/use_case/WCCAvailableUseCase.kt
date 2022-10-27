package com.example.cryptocurrencyapp.domain.use_case

import com.example.cryptocurrencyapp.data.repository.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class WCCAvailableUseCase (private val repository: WCCryptoRepositoryImp) {
    suspend fun invoke(): Flow<Resource<List<WCCryptoBookDTO>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getAvaliableBooks()
            emit(Resource.Success(response))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }

       /* if (response.)
        if (response.uiText != null){
            emit(Resource.Error(uiText = response.uiText /*?: UiText.unknownError()*/))
        }
        else{
            val data = response.data
            val books: WCCryptoBookDTO? = response.data
            emit(Resource.Success(books))
        }*/
    }
}