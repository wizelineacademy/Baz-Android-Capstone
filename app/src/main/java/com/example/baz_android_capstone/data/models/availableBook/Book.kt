package com.example.baz_android_capstone.data.models.availableBook // ktlint-disable package-name

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey
    val payload: List<Payload>,
    val success: Boolean
)

class BookConverter {

    @TypeConverter
    fun fromGroupTaskMemberList(value: List<Payload>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Payload>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<Payload> {
        val gson = Gson()
        val type = object : TypeToken<List<Payload>>() {}.type
        return gson.fromJson(value, type)
    }
}
