package com.brendarojas.criptomonedaswizeline.config

import android.app.Application
import com.brendarojas.criptomonedaswizeline.webservice.CryptoApi

class InitialApplication : Application() {
    companion object{
        lateinit var webServiceAvailableBooks: CryptoApi
    }

    override fun onCreate() {
        super.onCreate()
        webServiceAvailableBooks = ConfigRetrofit.getConfigurationRetrofit()
    }
}