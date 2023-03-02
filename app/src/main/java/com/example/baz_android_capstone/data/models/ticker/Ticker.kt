package com.example.baz_android_capstone.data.models.ticker // ktlint-disable package-name

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "ticker_table")
data class Ticker(
    @PrimaryKey
    val payload: Payload,
    val success: Boolean
)

class TickerConverter {

    @TypeConverter
    fun fromGroupTaskMemberList(value: Payload): String {
        val gson = Gson()
        val type = object : TypeToken<Payload>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): Payload {
        val gson = Gson()
        val type = object : TypeToken<Payload>() {}.type
        return gson.fromJson(value, type)
    }
}
