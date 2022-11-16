package com.baz.cmv.criptomonedas.coins.data.remote.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baz.cmv.criptomonedas.coins.data.remote.request.CoinsData

@Entity(tableName = "coins_table")
class CoinsEntity(@PrimaryKey(autoGenerate = true)
                  @ColumnInfo(name = "id") val id: Int= 0,
                  @ColumnInfo(name = "book") val book: String,
                  @ColumnInfo(name = "maximumAmount") val maximumAmount: String,
                  @ColumnInfo(name = "maximum_price") val maximum_price: String,
                  @ColumnInfo(name = "maximum_value") val maximum_value: String,
                  @ColumnInfo(name = "minimum_amount") val minimum_amount: String,
                  @ColumnInfo(name = "minimum_price") val minimum_price: String,
                  @ColumnInfo(name = "minimum_value") val minimum_value: String
)
fun CoinsData.toDataBase() = CoinsEntity(
    book = book,
    maximumAmount = maximumAmount,
    maximum_price = maximum_price,
    maximum_value = maximum_value,
    minimum_amount = minimum_amount,
    minimum_price = minimum_price,
    minimum_value = minimum_value
)
