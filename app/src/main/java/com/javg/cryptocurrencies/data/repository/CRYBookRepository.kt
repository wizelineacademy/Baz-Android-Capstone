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

/**
 * @author Juan Vera Gomez
 *
 * It is in charge of the functionality to be able to obtain the information
 * from the database and in case of not having information,
 * obtain it remotely through a service
 *
 * @since 2.0
 */
class CRYBookRepository  @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val cryApi: CRYApi,
    private val bookDao: CRYBookDao
): CRYGenericRepository() {

    /**
     * Returns a list of books retrieved from the database
     * or remotely from a service
     *
     * Which does a transformation from entity model to a
     * general model to return to the view
     *
     * @return List of books of model general
     */
    suspend fun getAvailableBooks(): List<CRYBook>{
        var localBooks = bookDao.getAllBook()

        if (localBooks.isEmpty()){
            val remoteBooks = getBooksFromApi()
            bookDao.insertAll(remoteBooks)
            localBooks = bookDao.getAllBook()
        }

        return localBooks.map { it.toDomain() }
    }

    /**
     * Returns a list of entity-type books to be stored
     * in the device's database, which was obtained from
     * the consumption of a remote information service.
     *
     * @return List of books entity
     */
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