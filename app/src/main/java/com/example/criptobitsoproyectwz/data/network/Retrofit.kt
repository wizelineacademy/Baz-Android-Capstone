package com.example.criptobitsoproyectwz.data.network

import com.example.criptobitsoproyectwz.Util.Constans.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//singlenton
object Retrofit {
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //cuando se hace la peticion convierta //Moshi cambia el modelo
            .build()
    }
}