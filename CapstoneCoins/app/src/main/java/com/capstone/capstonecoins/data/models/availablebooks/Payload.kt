package com.capstone.capstonecoins.data.models.availablebooks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Payload")
data class Payload(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "default_chart") val default_chart: String,
    @ColumnInfo(name = "maximum_amount") val maximum_amount: String,
    @ColumnInfo(name = "maximum_price") val maximum_price: String,
    @ColumnInfo(name = "maximum_value") val maximum_value: String,
    @ColumnInfo(name = "minimum_amount") val minimum_amount: String,
    @ColumnInfo(name = "minimum_price") val minimum_price: String,
    @ColumnInfo(name = "minimum_value") val minimum_value: String,
    @ColumnInfo(name = "tick_size") val tick_size: String
) : Serializable