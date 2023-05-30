package com.androidcourse.data.datasource.local.database.room

import com.androidcourse.data.datasource.local.RowByIdLocaleDataSourceInterface
import com.androidcourse.data.datasource.local.database.room.dao.BookDetailsDao
import com.androidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import javax.inject.Inject

class BookDetailsLocalDatasourceImpl @Inject constructor(
    private val dao: BookDetailsDao
) : RowByIdLocaleDataSourceInterface<BookDetailEntity, String> {
    override suspend fun saveAll(items: List<BookDetailEntity>) = dao.insertAll(items)

    override suspend fun getAll(): List<BookDetailEntity> = dao.getAll()

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun addRow(item: BookDetailEntity) = dao.addRow(item)

    override suspend fun getRow(id: String): BookDetailEntity {
        return dao.getBookDetail(id).let {
            it
        } ?: BookDetailEntity()
    }
}
