package com.carteagal.baz_android.data.network

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData

class CheckInternetConnection(private val conecctivityManager: ConnectivityManager) : LiveData<Boolean>() {

    constructor(application: Application) : this(application.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager)

    private val networkCallback = object : NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        conecctivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        conecctivityManager.unregisterNetworkCallback(networkCallback)
    }
}