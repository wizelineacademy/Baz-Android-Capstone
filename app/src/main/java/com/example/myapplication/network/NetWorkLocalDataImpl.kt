package com.example.myapplication.network

import com.example.myapplication.data.db.dao.CriptoCurrencyDAO
import com.example.myapplication.model.CriptoCurrency
import javax.inject.Inject

class NetWorkLocalDataImpl @Inject constructor(private val criptoCurrencyDAO: CriptoCurrencyDAO) :
    NetWorkLocalData {


    override suspend fun loadCriptoCurrency(): List<CriptoCurrency> =
        criptoCurrencyDAO.getCriptoList()

    override suspend fun saveCripto(data: List<CriptoCurrency>) {
        var conta = 0
        data.forEach {
            criptoCurrencyDAO.insertAll(it.apply { id = conta++ })
        }

    }
}