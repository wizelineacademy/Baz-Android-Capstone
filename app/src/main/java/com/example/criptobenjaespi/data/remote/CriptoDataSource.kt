package com.example.criptobenjaespi.data.remote

import com.example.criptobenjaespi.data.model.CriptoList

class CriptoDataSource {

    fun getCriptoList(): CriptoList{
        return CriptoList()
    }

}