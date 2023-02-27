package com.example.wizelineandroid.repository

import android.app.Application
import com.example.wizelineandroid.data.local.BookRoomDataBase

class RoomApplication : Application(){
    val database: BookRoomDataBase by lazy { BookRoomDataBase.getDatabase(this) }
}