package com.ari.coins.framework.data.local.entities.converters

import androidx.room.TypeConverter
import com.ari.coins.framework.data.local.entities.StructureEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * @author        Ari Valencia
 * @file          StructureConverter
 * @description   Converters for [List<StructureEntity>] using in Room DB
 */

class StructureConverter {

    @TypeConverter
    fun fromStructureEntityList(list: List<StructureEntity>?): String? {
        if (list == null) return null
        val type: Type = object : TypeToken<List<StructureEntity>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toStructureEntityList(str: String?): List<StructureEntity> {
        if (str == null) return emptyList()
        val type: Type = object : TypeToken<List<StructureEntity>>() {}.type
        return Gson().fromJson(str, type)
    }

}