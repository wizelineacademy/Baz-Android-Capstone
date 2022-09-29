package com.proyect.cursowizline.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.proyect.cursowizline.domain.model.CryptoM

@Entity(tableName = "crypto_table")
data class CryptoEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")val id: Int = 0,
    @ColumnInfo (name = "book")val book: String,
    @ColumnInfo (name = "minimum_price")val minimum_price: String,
    @ColumnInfo (name = "maximum_price")val maximum_price: String,
    @ColumnInfo (name = "minimum_value")val minimum_value: String,
    @ColumnInfo (name = "maximum_value")val maximum_value: String,
    @ColumnInfo (name = "tick_size")val tick_size: String

)

fun CryptoM.toDatabase() = CryptoEntity(book = book, minimum_price = minimum_price, maximum_price = maximum_price,
    minimum_value = minimum_value, maximum_value = maximum_value, tick_size = tick_size)