package com.course.criptomonedas.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "currency"
)
data class BooksEntity(
    @ColumnInfo(name = "name") var name: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
