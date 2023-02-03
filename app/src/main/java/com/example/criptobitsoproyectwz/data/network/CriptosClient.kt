package com.example.criptobitsoproyectwz.data.network

import com.example.criptobitsoproyectwz.data.DataSource.criptoDataSource
import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CriptosClient @Inject constructor(private val criptoService: BitsoService): criptoDataSource{

    override suspend fun getAllCriptos(): Response<BaseResult> = criptoService.getCriptos()

    //destapar el regalo
    /* fun getAllCriptos2(onNetworkResponse: (response: List<Payload>)->Unit){
        critoService.getCriptosService().enqueue(object : Callback<BaseResult>{
            override fun onResponse(call: Call<BaseResult>, response: Response<BaseResult>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        onNetworkResponse(it.payload.filter { it.book.contains("mxn") })
                    }
                }else{
                    onNetworkResponse(emptyList())
                }
            }

            override fun onFailure(call: Call<BaseResult>, t: Throwable) {
                onNetworkResponse(emptyList())
            }
        })
    }*/

}