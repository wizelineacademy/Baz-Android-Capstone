package com.example.bazandroidcourse.data.datasource.local.database.room

import com.example.bazandroidcourse.data.datasource.local.GeneralLocalDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BooksDao
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookEntity
import javax.inject.Inject

class BooksLocalDataSourceImpl @Inject constructor(
    private val dao: BooksDao
): GeneralLocalDataSourceInterface<BookEntity> {
    override suspend fun saveAll(items: List<BookEntity>) = dao.insertAll(items)

    override suspend fun getAll(): List<BookEntity> =   dao.getAll()

    override suspend fun deleteAll() =  dao.deleteAll()

    override suspend fun addRow(item: BookEntity) = dao.addRow(item)
}