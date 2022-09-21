package com.brendarojas.criptomonedaswizeline.config

import android.app.Application
import com.brendarojas.criptomonedaswizeline.webservice.CryptoApi

class InitialApplication : Application() {
    companion object{
        lateinit var webServiceGlobal: CryptoApi
    }

    override fun onCreate() {
        super.onCreate()
        webServiceGlobal = ConfigRetrofit.getConfigurationRetrofit()
    }
}