package com.javg.cryptocurrencies.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.google.gson.Gson
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import java.text.SimpleDateFormat
import java.util.*

object CRYUtils {

    /**
     * Checks if the device is connected to a network source,
     * be it mobile or wifi and returns a true or false
     * depending on whether or not it has a connection
     */
    fun isInternetAvailable(context: Context, onChangeState: (isConnected: Boolean) -> Unit): Boolean {
        val TAG = "isInternetAvailable"
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        onChangeState.invoke(true)
                        Log.e(TAG, "====================== The default network is now: " + network)
                    }

                    override fun onLost(network: Network) {
                        onChangeState.invoke(false)
                        Log.e(TAG, "====================== The application no longer has a default network. The last default network was " + network)
                    }

                    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                        Log.e(TAG, "====================== The default network changed capabilities: " + networkCapabilities)
                    }

                    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                        Log.e(TAG, "====================== The default network changed link properties: " + linkProperties)
                    }
                })
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }

    /**
     * Gets device time with mexico time zone setting and returns it
     */
    @SuppressLint("SimpleDateFormat")
    fun getCurrentDay(): String {
        val myTimeZone = TimeZone.getTimeZone("America/Mexico_City")
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        simpleDateFormat.timeZone = myTimeZone
        return simpleDateFormat.format(Date())
    }

    /**
     * Save a time in device preferences
     *
     * @param context is the context of the activity
     */
    fun saveTime(context: Context) {
        val sharedPreference = context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("time", getCurrentDay())
        editor.apply()
    }

    /**
     * Returns the time saved in device preferences
     *
     * @param context is the context of the activity
     */
    fun getSaveTime(context: Context): String {
        val sharedPreference = context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        return sharedPreference.getString("time", "") ?: ""
    }

    /**
     * Convert a list of type CRYAskOrBids to a string
     */
    fun convertersListToJson(askOrBids: List<CRYAskOrBids>): String = Gson().toJson(askOrBids)

    /**
     * Converts a string to a list of type CRYAskOrBids
     */
    fun convertersJsonToList(askOrBids: String) = Gson().fromJson(askOrBids, Array<CRYAskOrBids>::class.java).toList()
}
