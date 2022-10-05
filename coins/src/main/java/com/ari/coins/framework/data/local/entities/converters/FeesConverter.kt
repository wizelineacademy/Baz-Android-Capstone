package com.ari.coins.framework.data.local.entities.converters

import androidx.room.TypeConverter
import com.ari.coins.framework.data.local.entities.FeesEntity
import com.google.gson.Gson

/**
 * @author Ari Valencia
 * @file FeesConverter
 * @description Converters for [FeesEntity] using in Room DB
 */

class FeesConverter {

    @TypeConverter
    fun fromDatesEntity(fees: FeesEntity): String = Gson().toJson(fees, FeesEntity::class.java)

    @TypeConverter
    fun toMDatesEntity(str: String): FeesEntity = Gson().fromJson(str, FeesEntity::class.java)
}
