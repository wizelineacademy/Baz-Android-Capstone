package com.jpgl.cryptocurrencies.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jpgl.cryptocurrencies.R
import com.jpgl.cryptocurrencies.databinding.ActivityMainBinding
import com.jpgl.cryptocurrencies.ui.viewModel.CryptoViewModel
import com.jpgl.cryptocurrencies.utils.RequestState
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import com.jpgl.cryptocurrencies.utils.BaseUtils

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val cryptoViewModel: CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        BaseUtils.context = applicationContext
        setContentView(binding.root)


    }
}