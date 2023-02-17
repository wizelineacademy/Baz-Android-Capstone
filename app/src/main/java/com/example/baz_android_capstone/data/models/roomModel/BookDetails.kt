package com.example.baz_android_capstone.data.models.roomModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.baz_android_capstone.data.models.orderBook.Ask
import com.example.baz_android_capstone.data.models.orderBook.Bid
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "book_table")
data class BookDetails(
    @PrimaryKey
    var book: String,
    var high: String,
    var last: String,
    var low: String,
    var asks: List<Ask>,
    var bids: List<Bid>
)

class AskConverters {

    @TypeConverter
    fun fromGroupTaskMemberList(value: List<Ask>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Ask>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<Ask> {
        val gson = Gson()
        val type = object : TypeToken<List<Ask>>() {}.type
        return gson.fromJson(value, type)
    }
}

class BidConverters {

    @TypeConverter
    fun fromGroupTaskMemberList(value: List<Bid>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Bid>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<Bid> {
        val gson = Gson()
        val type = object : TypeToken<List<Bid>>() {}.type
        return gson.fromJson(value, type)
    }
}
