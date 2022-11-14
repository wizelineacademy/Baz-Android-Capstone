package com.example.bazandroidcourse.data.utils.network

interface NetworkManagerInterface {
    /***
     * Allows know if the device has a internet connection.
     * @return true if the device has a internet connection.
     */
    fun isOnline(): Boolean
}
