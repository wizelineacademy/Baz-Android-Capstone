package com.example.myapplication.network

import com.example.myapplication.model.CriptoResponse

/**
 * Created by: Juan Antonio Amado
 * date: 24,septiembre,2022
 */
interface NetwokDataSource {

    suspend fun loadCriptoCurrency() : CriptoResponse
}