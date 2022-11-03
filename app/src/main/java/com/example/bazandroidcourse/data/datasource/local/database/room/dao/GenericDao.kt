package com.example.bazandroidcourse.data.datasource.local.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface GenericDao<T> {
     @Insert
     suspend fun insertAll(items:List<T>)
     @Delete
     suspend fun deleteAll()
     @Insert
     suspend fun addRow(item:T)
}