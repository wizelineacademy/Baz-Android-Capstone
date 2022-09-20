package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.ApiRetro
import com.example.myapplication.api.interfaces.ApiBitsoInterface
import com.example.myapplication.model.CriptoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by: Juan Antonio Amado
 * date: 16,septiembre,2022
 */
class BitsoViewModel : ViewModel() {
    var moneyCripto: MutableLiveData<CriptoResponse?> = MutableLiveData()

    fun getCriptoCurrency(): MutableLiveData<CriptoResponse?> {
        return moneyCripto
    }

    fun consultCriptoCurrency() {
        val webService = ApiRetro.getRetorInstance().create(ApiBitsoInterface::class.java)
        val call = webService.getCripto()
        call.enqueue(object : Callback<CriptoResponse?> {
            override fun onResponse(
                call: Call<CriptoResponse?>,
                response: Response<CriptoResponse?>
            ) {
                if (response.isSuccessful) {
                    moneyCripto.postValue(response.body())
                } else {
                    moneyCripto.postValue(null)
                }

            }

            override fun onFailure(call: Call<CriptoResponse?>, t: Throwable) {
                moneyCripto.postValue(null)
            }

        })
    }
}