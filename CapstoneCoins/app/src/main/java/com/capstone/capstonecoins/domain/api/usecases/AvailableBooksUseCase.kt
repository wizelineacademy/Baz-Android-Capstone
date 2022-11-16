package com.capstone.capstonecoins.domain.api.usecases

import android.util.Log
import com.capstone.capstonecoins.data.repository.CoinsRepositoryImpl
import com.capstone.capstonecoins.data.repository.models.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AvailableBooksUseCase
@Inject constructor(private var repository: CoinsRepositoryImpl) {
    lateinit var response: List<Book>

    suspend fun book(): Flow<List<Book>> = flow {
        try {
            if (repository.getAvailableBooks().isNotEmpty()) {
                response = repository.getLocalBooks()
            } else {
                response = repository.getAvailableBooks()
                repository.insertLocalBooks(response)
            }
            emit(response)
        } catch (e: HttpException) {
            Log.d("Mensaje", "Show Error: $e")
        }
    }

}