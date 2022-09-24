package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ApiRetro
import com.example.myapplication.api.RetroFitRxClient
import com.example.myapplication.api.interfaces.ApiBitsoService
import com.example.myapplication.model.*
import com.example.myapplication.useCases.BitsoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by: Juan Antonio Amado
 * date: 16,septiembre,2022
 */
@HiltViewModel
class BitsoViewModel @Inject constructor(private val bitsoUseCase: BitsoUseCase) : ViewModel() {
    var moneyCripto: MutableLiveData<List<CriptoCurrency>> = MutableLiveData()
    var selectMoneyCripto: MutableLiveData<SelectCriptoResponse?> = MutableLiveData()
    var askBidMoneyCripto: MutableLiveData<AskAndBidResponse?> = MutableLiveData()

    fun getCriptoCurrency(): MutableLiveData<List<CriptoCurrency>> {
        return moneyCripto
    }

    fun getSelectCriptoCurrency(): MutableLiveData<SelectCriptoResponse?> {
        return selectMoneyCripto
    }

    fun getAskBidCriptoCurrency() : MutableLiveData<AskAndBidResponse?>{
        return askBidMoneyCripto
    }


    fun consultCriptoCurrency() {
        viewModelScope.launch {
            val result = bitsoUseCase()
            moneyCripto.postValue(result)
        }
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
