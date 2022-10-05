package com.ari.coins.framework.data.local.entities.converters

import androidx.room.TypeConverter
import com.ari.coins.framework.data.local.entities.AskEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * @author Ari Valencia
 * @file AskConverter
 * @description Converters for [List<AskEntity>] using in Room DB
 */

class AskConverter {

    @TypeConverter
    fun fromAskEntityList(list: List<AskEntity>?): String? {
        if (list == null) return null
        val type: Type = object : TypeToken<List<AskEntity>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toAskEntityList(str: String?): List<AskEntity> {
        if (str == null) return emptyList()
        val type: Type = object : TypeToken<List<AskEntity>>() {}.type
        return Gson().fromJson(str, type)
    }
}
