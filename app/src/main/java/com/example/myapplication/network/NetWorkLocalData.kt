package com.example.myapplication.network

import com.example.myapplication.model.CriptoCurrency
import com.example.myapplication.model.CriptoResponse

/**
 * Created by: Juan Antonio Amado
 * date: 26,septiembre,2022
 */
interface NetWorkLocalData {

    suspend fun loadCriptoCurrency() : List<CriptoCurrency>

    suspend fun saveCripto(data: List<CriptoCurrency>)
}