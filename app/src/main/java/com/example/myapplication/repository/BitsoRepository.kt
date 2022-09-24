package com.example.myapplication.repository

import com.example.myapplication.model.CriptoResponse

/**
 * Created by: Juan Antonio Amado
 * date: 21,septiembre,2022
 */
interface BitsoRepository {

   suspend fun loadCripto(): CriptoResponse

}