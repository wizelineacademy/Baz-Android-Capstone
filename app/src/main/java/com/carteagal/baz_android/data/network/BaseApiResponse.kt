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

suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
): Resources<T> =
    withContext(Dispatchers.IO) {
        try {
            val response = apiCall.invoke()
            Log.d("__tag response", response.body().toString())
            if(response.isSuccessful){
                val body = response.body() as BaseServiceResponse<*>
                Log.d("__tag body sucess", body.success.toString())
                Log.d("__tag body result", body.result.toString())
                if(body.success) Success(body.result)
                else Error("${body.error!!.code} ${body.error.message}")
            }
            error("${response.code()} ${response.message()}")
        }catch (e: Exception){
            Log.d("__tag exepton 2", e.message.toString())
            error(e.message ?: e.toString())
        }
    }

private fun <T>error(errorMessage: String): Resources<T> =
    Error("Api call failed $errorMessage")

