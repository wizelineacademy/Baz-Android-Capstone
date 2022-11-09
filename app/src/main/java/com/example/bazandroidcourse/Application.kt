package com.example.bazandroidcourse

import android.app.Application
import com.example.bazandroidcourse.data.datasource.local.database.room.core.dataBaseHelper
import com.example.bazandroidcourse.data.utils.network.networkManagerUtils

class Application():Application(){
    override fun onCreate() {
        super.onCreate()
        /**
         * this is important
         * todo:Inject this with some framework
         */
        networkManagerUtils.context = this

        /***
         * this is important
         * todo:Inject this with some framework
         */
        dataBaseHelper.context = this

    }

}