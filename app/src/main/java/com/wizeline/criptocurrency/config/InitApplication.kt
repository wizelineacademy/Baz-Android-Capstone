package com.wizeline.criptocurrency.config

import android.app.Application
import androidx.room.Room
import com.wizeline.criptocurrency.data.database.CriptoCurrencyDB

class InitApplication : Application() {
    companion object {
        lateinit var criptoCurrencyDB: CriptoCurrencyDB
    }

    override fun onCreate() {
        super.onCreate()
        criptoCurrencyDB = Room.databaseBuilder(
            this,
            CriptoCurrencyDB::class.java,
            "criptoCurrencyDB"
        ).allowMainThreadQueries()
            .build()
    }
}
