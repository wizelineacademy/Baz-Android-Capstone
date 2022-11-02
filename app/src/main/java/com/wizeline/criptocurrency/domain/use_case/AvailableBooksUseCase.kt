package com.wizeline.criptocurrency.domain.model.use_case

import com.wizeline.criptocurrency.common.adapters.RequestState
import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.data.repository.BitsoRepository
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.domain.model.Ticker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AvailableBooksUseCase @Inject constructor (private val repository: BitsoRepository) {

    operator fun invoke(): Flow<RequestState<List<AvailableBook>>> = flow {
        try {
            emit(RequestState.Loading<List<AvailableBook>>())
            val availableBooks: MutableList<AvailableBook> = mutableListOf()

                repository.getAvailableBooks().let {
                (it.body() as AvailableBooksResponse).payload?.forEach { availableBookDto->
                    availableBooks.add(availableBookDto.toAvailableBook())
                }
            }
            emit(RequestState.Success(availableBooks.toList()))
        } catch (e: HttpException) {
            emit(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(RequestState.Error("Couldn't reach server. Check your internet connection."))
            }
    }


}