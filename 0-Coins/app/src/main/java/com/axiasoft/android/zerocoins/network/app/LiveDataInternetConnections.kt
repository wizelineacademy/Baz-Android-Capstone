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

class InternetConnectionAvailableLiveData(private val connectivityManager: ConnectivityManager) :
    LiveData<Boolean>() {

    constructor(appContext: Application = ZeroCoinsApplication.appContext as Application) : this(
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    )

    fun isNetworkAvailable(): Boolean {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        val internetAvailable = validateNetworkAvailability(actNw)
        log("z0", "isNetworkAvailable? $internetAvailable")
        return internetAvailable
    }

    private fun validateNetworkAvailability(networkCapabilities: NetworkCapabilities): Boolean{
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }


    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            log("Z0", "-> onAvailable: Network ${network} is Available")
            postValue(true)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val internetAvailable = validateNetworkAvailability(networkCapabilities)

            if (internetAvailable) {
                log("Z0", "onCapabilitiesChanged: Network ${network} is true?")
                postValue(true)
            } else {
                log("Z0", "onCapabilitiesChanged: Network ${network} is false?")
                postValue(false)
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            log("Z0", "-> onLost: Network ${network} is Unavailable")
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager.registerNetworkCallback(builder, networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}