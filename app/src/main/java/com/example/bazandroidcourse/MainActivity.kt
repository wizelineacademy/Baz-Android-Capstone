package com.example.bazandroidcourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bazandroidcourse.data.utils.network.networkManagerUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        networkManagerUtils.context = this
    }
}