package com.example.myapplication.network

import com.example.myapplication.api.interfaces.ApiBitsoService
import com.example.myapplication.model.CriptoResponse
import com.example.myapplication.model.SelectCriptoResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NetwokDataSourceImpl @Inject constructor(private val apiBitsoService: ApiBitsoService) :
    NetwokDataSource, BaseDataSource() {

    override suspend fun loadCriptoCurrency(): CriptoResponse = getResult { apiBitsoService.getCripto()}

    override fun getSelectCripto(id: String): Single<SelectCriptoResponse> = apiBitsoService.getSelectCripto(id)
}