package com.example.baz_android_capstone.data.models.orderBook

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Payload(
    val asks: List<Ask>,
    val bids: List<Bid>
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
