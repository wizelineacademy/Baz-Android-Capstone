package com.javg.cryptocurrencies.data.repository

import android.content.Context
import com.javg.cryptocurrencies.data.db.dao.CRYBookDao
import com.javg.cryptocurrencies.data.model.CRYBaseResponse
import com.javg.cryptocurrencies.data.model.CRYBookResponse
import com.javg.cryptocurrencies.data.network.CRYApi
import com.javg.cryptocurrencies.utils.CRYUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

//tomar en cuenta no inyectar una libreria
class CRYBookRepository  @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val cryApi: CRYApi,
    private val bookDao: CRYBookDao
): CRYGenericRepository() {
    suspend fun getAvailableBooks(): CRYBaseResponse<List<CRYBookResponse>>{
        var responseAux = CRYBaseResponse<List<CRYBookResponse>>()
        val isInternetAvailable = CRYUtils.isInternetAvailable(appContext)
        if (isInternetAvailable) {
            println("isInternetAvailable --------------------------------- si hay internet")
            getResponse { responseAux = cryApi.getListAvailableBooks() }
            bookDao.insertAll(responseAux.payload!!)
        } else {
            println("isInternetAvailable --------------------------------- No hay internet")
            responseAux.payload = bookDao.getAllBook()
        }

        return responseAux
    }

}