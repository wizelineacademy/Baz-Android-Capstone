package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.ApiRetro
import com.example.myapplication.api.RetroFitRxClient
import com.example.myapplication.api.interfaces.ApiBitsoService
import com.example.myapplication.model.AskAndBidResponse
import com.example.myapplication.model.CriptoResponse
import com.example.myapplication.model.SelectCriptoResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by: Juan Antonio Amado
 * date: 16,septiembre,2022
 */
class BitsoViewModel : ViewModel() {
    var moneyCripto: MutableLiveData<CriptoResponse?> = MutableLiveData()
    var selectMoneyCripto: MutableLiveData<SelectCriptoResponse?> = MutableLiveData()
    var askBidMoneyCripto: MutableLiveData<AskAndBidResponse?> = MutableLiveData()

    fun getCriptoCurrency(): MutableLiveData<CriptoResponse?> {
        return moneyCripto
    }

    fun getSelectCriptoCurrency(): MutableLiveData<SelectCriptoResponse?> {
        return selectMoneyCripto
    }

    fun getAskBidCriptoCurrency() : MutableLiveData<AskAndBidResponse?>{
        return askBidMoneyCripto
    }


    fun consultCriptoCurrency() {
        val webService = ApiRetro.getRetorInstance().create(ApiBitsoService::class.java)
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

    fun selectCriptoCurrency(id: String) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetroFitRxClient.buildService2()
                .getSelectCripto(id = id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { onSuccess: SelectCriptoResponse?, onError: Throwable? ->
                    onSuccess?.let {
                        selectMoneyCripto.postValue(it)
                    }

                    onError?.let {
                        selectMoneyCripto.postValue(null)
                    }
                })
    }

    fun getAskAndBidsCurrency(id: String) {
        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
            RetroFitRxClient.buildService2()
                .getAskAndBids(id = id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{ onSuccess: AskAndBidResponse?, onError: Throwable? ->
                    onSuccess?.let {
                        askBidMoneyCripto.postValue(it)
                    }

                    onError?.let {
                        selectMoneyCripto.postValue(null)
                    }
                })
    }
}