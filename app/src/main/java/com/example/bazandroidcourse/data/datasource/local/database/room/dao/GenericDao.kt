package com.example.bazandroidcourse.data.datasource.local.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface GenericDao<T> {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAll(items:List<T>)

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addRow(item:T)
}