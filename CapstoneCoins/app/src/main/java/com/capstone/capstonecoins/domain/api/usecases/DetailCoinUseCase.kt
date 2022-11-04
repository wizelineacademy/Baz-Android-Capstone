package com.capstone.capstonecoins.domain.api.usecases

import android.util.Log
import com.capstone.capstonecoins.data.models.ticker.tickerquery.TickerWithQuery
import com.capstone.capstonecoins.data.repository.DetailCoinRepositoryImpl
import com.capstone.capstonecoins.data.repository.models.BookDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class DetailCoinUseCase(private var repository: DetailCoinRepositoryImpl) {
    suspend fun detailCoin(typeCoin: String): Flow<BookDetail> = flow {
        try {
            val response = repository.getDetailCoin(typeCoin)
            emit(response)
        } catch (e: HttpException) {
            Log.d("Mensaje", "Show Error: $e")
        }
    }
}