package com.example.myapplication.repository

import com.example.myapplication.model.CriptoResponse
import com.example.myapplication.network.NetwokDataSource
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(private val netwokrDataSource: NetwokDataSource) :
    BitsoRepository {

    override suspend fun loadCripto(): CriptoResponse = netwokrDataSource.loadCriptoCurrency()
}