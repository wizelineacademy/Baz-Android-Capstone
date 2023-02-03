package com.example.myapplication.api.interfaces

import com.example.myapplication.model.AskAndBidResponse
import com.example.myapplication.model.CriptoResponse
import com.example.myapplication.model.SelectCriptoResponse
import io.reactivex.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by: Juan Antonio Amado
 * date: 16,septiembre,2022
 */
interface ApiBitsoService {

    @GET("available_books/")
    fun getCripto(): Call<CriptoResponse>

    @GET("ticker/?")
    fun getSelectCripto(@Query("book") id:String): Single<SelectCriptoResponse>

    @GET("order_book/?")
    fun getAskAndBids(@Query("book") id:String): Single<AskAndBidResponse>

}