package com.example.myapplication.network

import com.example.myapplication.model.CriptoResponse
import com.example.myapplication.model.SelectCriptoResponse
import io.reactivex.rxjava3.core.Single

/**
 * Created by: Juan Antonio Amado
 * date: 24,septiembre,2022
 */
interface NetwokDataSource {

    suspend fun loadCriptoCurrency() : CriptoResponse

    fun getSelectCripto(id: String) : Single<SelectCriptoResponse>
}