package com.example.cryptocurrency_challenge.network.api.config

import android.app.Application
import com.example.cryptocurrency_challenge.network.api.ApiBitso

class StartApplication : Application(){

    companion object{
        lateinit var webServiceGlobal: ApiBitso
    }

    override fun onCreate() {
        super.onCreate()
        webServiceGlobal=RetrofitConfig().obtenerConfiguracionRetrofit()
    }
}