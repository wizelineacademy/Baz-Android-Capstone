package com.example.myapplication.network

import com.example.myapplication.model.CriptoResponse

/**
 * Created by: Juan Antonio Amado
 * date: 26,septiembre,2022
 */
abstract class BaseDataSource {

    protected suspend fun getResult(call: suspend () -> CriptoResponse): CriptoResponse {
        try {
            val response = call()
            if (response.success) {
                return response
            }
            return CriptoResponse(listOf(),false)
        } catch (e: Exception) {
            return CriptoResponse(listOf(),false)
        }
    }

}