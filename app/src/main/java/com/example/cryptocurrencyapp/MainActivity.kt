package com.example.cryptocurrencyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.cryptocurrencyapp.data.repository.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding
import com.example.cryptocurrencyapp.domain.repository.retrofit
import com.example.cryptocurrencyapp.domain.use_case.OrderUseCase
import com.example.cryptocurrencyapp.domain.use_case.DetailUseCase
import com.example.cryptocurrencyapp.presentation.view_model.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val viewModelTicker : DetailViewModel by viewModels {
        ViewModelFactoryTicker(DetailUseCase(WCCryptoRepositoryImp(retrofit)))
    }
    private val viewModelOrder : OrderViewModel by viewModels {
        ViewModelFactoryOrder(OrderUseCase(WCCryptoRepositoryImp(retrofit)))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModelTicker.getTicker("eth_mxn")
        viewModelOrder.getOrderBook("eth_mxn")
    }

}