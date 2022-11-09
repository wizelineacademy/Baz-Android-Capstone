package com.example.bazandroidcourse.data.datasource.local.database.room.core

import android.app.Application
import androidx.room.Room

object dataBaseHelper {
    var context: Application? = null
    fun getInstance(): AppDataBase? {
        return context?.let {
             Room.databaseBuilder(
                 it,
                AppDataBase::class.java,
                "com_mx_baz_android_course_db"
            ).build()
        }
    }
}