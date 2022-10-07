package com.baz.cmv.criptomonedas.coins.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.baz.cmv.criptomonedas.coins.CoinsDetail
import com.baz.cmv.criptomonedas.coins.core.RetrofitService
import com.baz.cmv.criptomonedas.coins.data.remote.network.CoinsApiClient
import com.baz.cmv.criptomonedas.coins.data.remote.response.NetworkMoneda

class DetailViewModel : ViewModel() {
    val retrofit = RetrofitService.getRetrofitInstance()
    val coinsApiClient = retrofit.create(CoinsApiClient::class.java)



}
