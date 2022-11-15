package com.capstone.capstonecoins.domain.api.usecases

import android.util.Log
import com.capstone.capstonecoins.data.models.orderbook.OrderBooks
import com.capstone.capstonecoins.data.repository.DetailCoinRepositoryImpl
import com.capstone.capstonecoins.data.repository.models.BookDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DetailCoinUseCase @Inject constructor(private var repository: DetailCoinRepositoryImpl) {
    lateinit var response: BookDetail

    suspend fun detailCoin(typeCoin: String): Flow<BookDetail> = flow {
        try {
            if (repository.getLocalDetailCoin(typeCoin).id > 0) {
                response = repository.getLocalDetailCoin(typeCoin)
            } else {
                response = repository.getDetailCoin(typeCoin)
            }
            repository.insertLocalDetailBook(response)
            emit(response)
        } catch (e: HttpException) {
            Log.d("Mensaje", "Show Error: $e")
        }
    }

    suspend fun bidsAsksCoin(typeCoin: String): Flow<OrderBooks> = flow {
        try {
            val response = repository.getBidsAsksCoin(typeCoin)
            emit(response)
        } catch (e: HttpException) {
            Log.d("Mensaje", "Show Error: $e")
        }
    }

}