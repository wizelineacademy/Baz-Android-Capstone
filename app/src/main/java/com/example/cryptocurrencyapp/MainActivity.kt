package com.example.cryptocurrencyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.cryptocurrencyapp.data.repository.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.domain.repository.retrofit
import com.example.cryptocurrencyapp.domain.use_case.TickerUseCase
import com.example.cryptocurrencyapp.presentation.view_model.TickerViewModel
import com.example.cryptocurrencyapp.presentation.view_model.ViewModelFactoryTicker


class MainActivity : AppCompatActivity() {
    /*private  val viewModel: WCCAvailableVM by viewModels {
        ViewModelFactory(WCCAvailableUseCase(WCCryptoRepositoryImp(retrofit)))
    }*/
    private val viewModels : TickerViewModel by viewModels {
        ViewModelFactoryTicker(TickerUseCase(WCCryptoRepositoryImp(retrofit)))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModel.getAvailableBook()
        viewModels.getTicker("eth_mxn")
    }

}