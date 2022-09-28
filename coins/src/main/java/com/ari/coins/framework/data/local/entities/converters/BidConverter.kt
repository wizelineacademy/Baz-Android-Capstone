package com.ari.coins.framework.data.local.entities.converters

import androidx.room.TypeConverter
import com.ari.coins.framework.data.local.entities.BidEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class BidConverter {

    @TypeConverter
    fun fromBidEntityList(list: List<BidEntity>): String? {
        if (list == null) return null
        val type: Type = object : TypeToken<List<BidEntity>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toBidEntityList(str: String?): List<BidEntity> {
        if (str == null) return emptyList()
        val type: Type = object : TypeToken<List<BidEntity>>() {}.type
        return Gson().fromJson(str, type)
    }

}