package com.carteagal.baz_android.data.repository

import android.util.Log
import com.carteagal.baz_android.data.model.availableBook.AvailableBookResponse
import com.carteagal.baz_android.data.model.base.BaseServiceResponse
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.data.dataSources.CryptoRemoteDataSourceImp
import com.carteagal.baz_android.data.dataSources.CryptoRemoteDataSources
import javax.inject.Inject

class AvailableBooksRepository @Inject constructor(
    private val apiDataSource: CryptoRemoteDataSources
) {
    suspend fun getAllBooksNetwork(): List<AvailableBookResponse> {
        return apiDataSource.getAvailableBooks()
    }
}