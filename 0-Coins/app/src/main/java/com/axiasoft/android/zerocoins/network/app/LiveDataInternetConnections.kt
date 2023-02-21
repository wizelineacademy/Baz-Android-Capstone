package com.axiasoft.android.zerocoins.network.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import com.axiasoft.android.zerocoins.application.ZeroCoinsApplication
import com.axiasoft.android.zerocoins.common.log

class InternetConnectionAvailableLiveData(private val connectivityManager: ConnectivityManager): LiveData<Boolean>(){
    constructor(appContext: Application = ZeroCoinsApplication.appContext as Application) : this(
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    )

    private var currentStatus: Boolean = false

    var isNetworkAvailable = currentStatus//postValue(currentStatus)

    init {
        log("z0", "InternetConnectionAvailableLiveData created")
        val builder = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager.registerNetworkCallback(builder, object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                log("Z0", "-> onAvailable: Network ${network} is Available")
                currentStatus = true
                postValue(true)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
                //val metered = networkCapabilities.hasCapability(NetworkCapabilities.M)
                val internetCapability = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

                if (/*unmetered || */internetCapability){
                    log("Z0", "onCapabilitiesChanged: Network ${network} is true?")
                    currentStatus = true
                    postValue(true)
                } else {
                    log("Z0", "onCapabilitiesChanged: Network ${network} is false?")
                    currentStatus = false
                    postValue(false)
                }

            }

            override fun onLost(network: Network) {
                super.onLost(network)
                log("Z0", "-> onLost: Network ${network} is Unavailable")
                currentStatus = false
                postValue(false)
            }
        })
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            log("Z0", "-> onAvailable: Network ${network} is Available")
            currentStatus = true
            postValue(true)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
            //val metered = networkCapabilities.hasCapability(NetworkCapabilities.M)
            val internetCapability = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

            if (/*unmetered || */internetCapability){
                log("Z0", "onCapabilitiesChanged: Network ${network} is true?")
                currentStatus = true
                postValue(true)
            } else {
                log("Z0", "onCapabilitiesChanged: Network ${network} is false?")
                currentStatus = false
                postValue(false)
            }

        }

        override fun onLost(network: Network) {
            super.onLost(network)
            log("Z0", "-> onLost: Network ${network} is Unavailable")
            currentStatus = false
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()

        //ANDROID -> connectivityManager.requestNetwork(builder, networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        //connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}