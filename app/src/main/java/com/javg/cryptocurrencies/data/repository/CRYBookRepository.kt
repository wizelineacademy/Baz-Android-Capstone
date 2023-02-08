package com.javg.cryptocurrencies.data.repository

import android.content.Context
import com.javg.cryptocurrencies.data.db.dao.CRYBookDao
import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity
import com.javg.cryptocurrencies.data.mapper.toDomain
import com.javg.cryptocurrencies.data.mapper.toEntity
import com.javg.cryptocurrencies.data.model.CRYBaseResponse
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.model.CRYBookResponse
import com.javg.cryptocurrencies.data.network.CRYApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CRYBookRepository  @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val cryApi: CRYApi,
    private val bookDao: CRYBookDao
): CRYGenericRepository() {
    suspend fun getAvailableBooks(): List<CRYBook>{
        var localBooks = bookDao.getAllBook()

        if (localBooks.isEmpty()){
            val remoteBooks = getBooksFromApi()
            bookDao.insertAll(remoteBooks)
            localBooks = bookDao.getAllBook()
        }

        return localBooks.map { it.toDomain() }
    }

    private suspend fun getBooksFromApi(): List<CRYBookEntity>{
        var responseAux = CRYBaseResponse<List<CRYBookResponse>>()
        val listBookEntity = mutableListOf<CRYBookEntity>()
        getResponse{ responseAux = cryApi.getListAvailableBooks() }
        responseAux.payload?.let { payload ->
            payload.forEach {
                listBookEntity.add(it.toEntity())
            }
        }
        return listBookEntity
    }

}