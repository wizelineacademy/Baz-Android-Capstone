package com.example.bazandroidcourse.data.datasource.local.database.room

import com.example.bazandroidcourse.data.datasource.local.CollectionLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BookOrdersDao
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity

class BookOrdersLocalDataSourceImpl(
    private  val dao: BookOrdersDao
):CollectionLocaleDataSourceInterface<BookOrderEntity,String>{
    override suspend fun getAll(id: String): List<BookOrderEntity> {
        return dao.getAll(id)
    }

    override suspend fun saveAll(items: List<BookOrderEntity>) {
        dao.insertAll(items)
    }

    override suspend fun getAll(): List<BookOrderEntity> {
        return dao.getAll()
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun deleteAll(id:String) {
        dao.deleteAll(id)
    }

    override suspend fun addRow(item: BookOrderEntity) {
        dao.addRow(item)
    }
}