package com.carteagal.baz_android.domain.useCase

import com.carteagal.baz_android.data.model.availableBook.AvailableBookUI
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.data.network.Resources.Error
import com.carteagal.baz_android.data.network.Resources.Success
import com.carteagal.baz_android.data.repository.AvailableBooksRepository
import com.carteagal.baz_android.domain.mapper.availableMapper
import javax.inject.Inject

class GetAvailableBooksUseCase @Inject constructor(
    private val availableBooksRepository: AvailableBooksRepository
) {
    suspend operator fun invoke(): Resources<List<AvailableBookUI>>{
        return try {
            Success(availableMapper(availableBooksRepository.getAllBooksNetwork()))
        }catch (e: Exception){
            Error(e.message)
        }
    }
}