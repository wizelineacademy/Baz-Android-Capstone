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
import com.javg.cryptocurrencies.utils.CRYUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 * Date modified 10/02/2023
 *
 * It is in charge of the functionality to be able to obtain the information
 * from the database and in case of not having information,
 * obtain it remotely through a service
 *
 * @since 2.0
 */
class CRYBookRepository  @Inject constructor(@ApplicationContext val context: Context,
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
     * @param onRefresh flag that will indicate to the data layer if it
     * consults the remote information again according
     * to the user's interaction
     *
     * @return List of books of model general
     */
    suspend fun getAvailableBooks(onRefresh: Boolean = false): List<CRYBook>{
        var localBooks = bookDao.getAllBook()

        if (localBooks.isEmpty() || onRefresh){
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
            CRYUtils.saveTime(context)
            payload.forEach {
                listBookEntity.add(it.toEntity())
            }
        }
        return listBookEntity
    }

    fun queryBooks(): Flow<List<CRYBook>> = bookDao.getAllBookV2().map { books ->
        books.map { it.toDomain() }
    }

}