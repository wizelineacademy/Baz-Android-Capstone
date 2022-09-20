package com.example.myapplication.api.interfaces

import com.example.myapplication.model.CriptoResponse
import com.example.myapplication.model.UniqueCriptoResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by: Juan Antonio Amado
 * date: 16,septiembre,2022
 */
interface ApiBitsoInterface {

    @GET("available_books/")
    fun getCripto(): Call<CriptoResponse>

    @GET("?book=")
    fun getSelectCripto(): Call<UniqueCriptoResponse>
}