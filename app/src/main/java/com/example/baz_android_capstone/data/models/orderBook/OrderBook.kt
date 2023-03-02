package com.example.baz_android_capstone.data.models.orderBook // ktlint-disable package-name

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "order_table")
data class OrderBook(
    @PrimaryKey
    val payload: Payload,
    val success: Boolean
)

class OrderConverter {

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
