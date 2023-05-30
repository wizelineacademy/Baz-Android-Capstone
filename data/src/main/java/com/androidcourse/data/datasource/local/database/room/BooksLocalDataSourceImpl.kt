package com.androidcourse.data.datasource.local.database.room

import android.annotation.SuppressLint
import com.androidcourse.data.datasource.local.RXInterface
import com.androidcourse.data.datasource.local.database.room.dao.BooksDao
import com.androidcourse.data.datasource.local.database.room.entities.BookEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BooksLocalDataSourceImpl @Inject constructor(
    private val dao: BooksDao
) : RXInterface<BookEntity> {

    override suspend fun saveAll(items: List<BookEntity>) = dao.insertAll(items)

    override suspend fun getAll(): List<BookEntity> = dao.getAll()

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun addRow(item: BookEntity) = dao.addRow(item)

    @SuppressLint("CheckResult")
    override fun saveAllRx(items: List<BookEntity>) {
        Observable.just(dao)
            .subscribeOn(Schedulers.io())
            .subscribe {
                dao.insertAll(items)
            }
    }
}
