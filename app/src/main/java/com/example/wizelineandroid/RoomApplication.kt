package com.example.wizelineandroid

import android.app.Application
import com.example.wizelineandroid.data.local.BookRoomDataBase
import com.example.wizelineandroid.di.AppModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RoomApplication : Application() {
    val database: BookRoomDataBase by lazy { AppModule.provideDatabase(this) }
}
