package com.javg.cryptocurrencies.data.repository

import android.content.Context
import android.util.Log
import com.javg.cryptocurrencies.data.db.dao.CRYBookDao
import com.javg.cryptocurrencies.data.mapper.toDomain
import com.javg.cryptocurrencies.data.mapper.toEntity
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.network.CRYApi
import com.javg.cryptocurrencies.utils.CRYUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
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
class CRYBookRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val cryApi: CRYApi,
    private val bookDao: CRYBookDao,
) : CRYGenericRepository() {

    /**
     * Observe through a flow the list of books stored in the database every time it changes
     *
     * @return a book list type flow
     */
    fun queryBooks(): Flow<List<CRYBook>> = bookDao.getAllBookV2().map { books ->
        books.map { it.toDomain() }
    }

    /**
     * It is in charge of consuming the remote api to be able
     * to obtain the list of books and later store them in the database
     */
    fun getAvailableBooksRx(): Boolean {
        var result = false
        cryApi.getListAvailableBooksRX()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                result = if (response.payload.isNullOrEmpty()) {
                    false
                } else {
                    CRYUtils.saveTime(context)
                    response?.payload?.let { payload ->
                        val booksEntity = payload.map { it.toEntity() }

                        Observable.just(bookDao)
                            .subscribeOn(Schedulers.io())
                            .subscribe {
                                it.insertAll(booksEntity)
                            }
                    }
                    true
                }
            }) { throwable ->
                Log.e("CRYBookRepository", "Error $throwable")
                result = false
            }
        return result
    }
}
