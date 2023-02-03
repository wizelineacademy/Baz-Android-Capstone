package com.example.myapplication.api

import com.example.myapplication.api.interfaces.ApiBitsoService
import com.example.myapplication.model.SelectCriptoResponse
import io.reactivex.rxjava3.core.Single
import java.util.*

/**
 * Created by: Juan Antonio Amado
 * date: 20,septiembre,2022
 */
class BitsoClient(private val apiBitsoService: ApiBitsoService, private val id: String) {

    fun getSelectCurrency(): Single<SelectCriptoResponse> = apiBitsoService.getSelectCripto(id)
}