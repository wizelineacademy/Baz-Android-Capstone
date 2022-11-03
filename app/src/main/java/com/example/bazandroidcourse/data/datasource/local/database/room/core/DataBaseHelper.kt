package com.example.bazandroidcourse.data.datasource.local.database.room.core

import android.app.Application
import androidx.room.Room

class DataBaseHelper(private val context: Application) {
    fun applicationDatabase(): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "com_mx_baz_android_course_db"
        ).build()
    }
}