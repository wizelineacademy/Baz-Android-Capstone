package com.example.myapplication.repository

import com.example.myapplication.model.CriptoCurrency
import com.example.myapplication.model.CriptoResponse
import com.example.myapplication.model.SelectCriptoResponse
import io.reactivex.rxjava3.core.Single

/**
 * Created by: Juan Antonio Amado
 * date: 21,septiembre,2022
 */
interface BitsoRepository {

   suspend fun loadCripto(): CriptoResponse
   suspend fun loadCriptoList(): List<CriptoCurrency>
   suspend fun saveDataList(data: List<CriptoCurrency>)

   fun loadSelectCriptoCurrency(idBook: String): Single<SelectCriptoResponse>
}