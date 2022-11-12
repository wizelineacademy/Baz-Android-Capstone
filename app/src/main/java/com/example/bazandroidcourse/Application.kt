package com.example.bazandroidcourse

import android.app.Application
import com.example.bazandroidcourse.data.utils.network.networkManagerUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application():Application() {
    override fun onCreate() {
        super.onCreate()
        /**
         * this is important
         * todo:Inject this with some framework
         */
        networkManagerUtils.context = this



    }

}