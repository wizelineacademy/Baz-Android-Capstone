package com.carteagal.baz_android.data.network

import android.util.Log
import com.carteagal.baz_android.data.model.base.BaseServiceResponse
import com.carteagal.baz_android.data.network.Resources.Error
import com.carteagal.baz_android.data.network.Resources.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response


open class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resources<T> =
        withContext(Dispatchers.IO){
            try {
                val response = apiCall()
                if(response.isSuccessful){
                    val body = response.body()
                    body?.let {
                        Success(body)
                    }
                }
                error("${response.code()} ${response.message()}")
            }catch (e: Exception){
                 error(e.message ?: e.toString())
            }
        }

    private fun <T>error(errorMessage: String): Resources<T> =
        Error("Api call failed $errorMessage")
}

private fun <T>error(errorMessage: String): Resources<T> =
    Error("Api call failed $errorMessage")

