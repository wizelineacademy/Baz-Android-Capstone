package com.example.cryptocurrency_challenge.data.network.config

import android.app.Application
import com.example.cryptocurrency_challenge.data.network.ApiBitsoService

class StartApplication : Application(){

    companion object{
        lateinit var webServiceGlobal: ApiBitsoService
    }

    override fun onCreate() {
        super.onCreate()
        webServiceGlobal=RetrofitConfig().obtenerConfiguracionRetrofit()
    }
}