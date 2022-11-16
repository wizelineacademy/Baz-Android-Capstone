package com.wizeline.criptocurrency.domain.model.use_case

import com.wizeline.criptocurrency.common.adapters.RequestState
import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.domain.repository.BitsoRepository
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AvailableBooksUseCase @Inject constructor(private val repository: BitsoRepository) {

    operator fun invoke(): Flow<RequestState<List<AvailableBook>>> = flow {

        try {
            emit(RequestState.Loading<List<AvailableBook>>())

            val list = repository.getAvailableBooks()
            val filteredList = list.filter { it.book?.contains("mxn") ?: false }

            emit(RequestState.Success(filteredList))
        } catch (e: HttpException) {
            emit(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(RequestState.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    suspend fun availableBooksRx(): Single<AvailableBooksResponse> = repository.getAvailableBooksRxJava()
}
