package com.axiasoft.android.zerocoins.application

import android.app.Application
import android.content.Context
import android.content.res.Resources

class ZeroCoinsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
    companion object{
        lateinit var appContext: Context
    }
}