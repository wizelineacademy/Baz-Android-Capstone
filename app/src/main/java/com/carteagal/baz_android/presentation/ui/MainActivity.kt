package com.carteagal.baz_android.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.carteagal.baz_android.R
import com.carteagal.baz_android.data.network.CheckInternetConnection
import com.carteagal.baz_android.databinding.ActivityMainBinding
import com.carteagal.baz_android.presentation.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var checkNetworkConnection: CheckInternetConnection
    private val cryptoViewModel: CryptoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenSplash = installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callNetworkConnection()
        cryptoViewModel.getAvailableBooks()
        screenSplash.setKeepOnScreenCondition{ false }
    }

    private fun callNetworkConnection(){
        checkNetworkConnection = CheckInternetConnection(application)
        checkNetworkConnection.observe(this) {
            if (it) {
                Log.d("__tag func", "entro true")
            }else{
                Log.d("__tag func", "entro false")
            }
        }
    }
}