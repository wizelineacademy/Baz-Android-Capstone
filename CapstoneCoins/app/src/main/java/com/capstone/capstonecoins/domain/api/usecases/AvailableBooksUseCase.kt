package com.capstone.capstonecoins.domain.api.usecases

import android.util.Log
import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.data.repository.CoinsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AvailableBooksUseCase(private var repository: CoinsRepositoryImpl) {
    suspend fun book(): Flow<BooksDto> = flow {
        try {
            val response = repository.getAvailableBooks()
            Log.d("Mensaje", repository.getAvailableBooks().toString())
            emit(response)
        } catch (e: HttpException) {
            Log.d("Mensaje", "Show Error: $e")
        }
    }

}