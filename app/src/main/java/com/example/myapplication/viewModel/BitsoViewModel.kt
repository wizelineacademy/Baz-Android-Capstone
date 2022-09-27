package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.RetroFitRxClient
import com.example.myapplication.model.AskAndBidResponse
import com.example.myapplication.model.CriptoCurrency
import com.example.myapplication.model.SelectCriptoResponse
import com.example.myapplication.repository.BitsoRepository
import com.example.myapplication.useCases.LoadAllCriptoCurrencyUseCase
import com.example.myapplication.useCases.LoadCriptoWithFilterCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by: Juan Antonio Amado
 * date: 16,septiembre,2022
 */
@HiltViewModel
class BitsoViewModel @Inject constructor(
    private val loadCriptoWithFilterCurrencyUseCase: LoadCriptoWithFilterCurrencyUseCase,
    private val loadAllCriptoCurrencyUseCase: LoadAllCriptoCurrencyUseCase,
    private val bitsoRepository: BitsoRepository
) :
    ViewModel() {
    var moneyCripto: MutableLiveData<List<CriptoCurrency>?> = MutableLiveData()
    var selectMoneyCripto: MutableLiveData<SelectCriptoResponse?> = MutableLiveData()
    var askBidMoneyCripto: MutableLiveData<AskAndBidResponse?> = MutableLiveData()

    fun getCriptoCurrency(): MutableLiveData<List<CriptoCurrency>?> {
        return moneyCripto
    }

    fun getSelectCriptoCurrency(): MutableLiveData<SelectCriptoResponse?> {
        return selectMoneyCripto
    }

    fun getAskBidCriptoCurrency(): MutableLiveData<AskAndBidResponse?> {
        return askBidMoneyCripto
    }


    fun consultFilterCriptoCurrency() {
        viewModelScope.launch {
            val result = loadCriptoWithFilterCurrencyUseCase()
            moneyCripto.postValue(result)
        }
    }

    fun consultAllcriptoCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            if (bitsoRepository.loadCriptoList().isEmpty()){
                val result = loadAllCriptoCurrencyUseCase()
                if (result.isNotEmpty()){
                    bitsoRepository.saveDataList(result)
                    moneyCripto.postValue(result)
                }else {
                    moneyCripto.postValue(null)
                }

            } else {
                moneyCripto.postValue(bitsoRepository.loadCriptoList())
            }

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
                .subscribe { onSuccess: AskAndBidResponse?, onError: Throwable? ->
                    onSuccess?.let {
                        askBidMoneyCripto.postValue(it)
                    }

                    onError?.let {
                        selectMoneyCripto.postValue(null)
                    }
                })
    }
}
