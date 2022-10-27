package com.example.cryptocurrencyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.cryptocurrencyapp.data.repository.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.domain.repository.retrofit
import com.example.cryptocurrencyapp.domain.use_case.WCCAvailableUseCase
import com.example.cryptocurrencyapp.presentation.view_model.ViewModelFactory
import com.example.cryptocurrencyapp.presentation.view_model.WCCAvailableVM


class MainActivity : AppCompatActivity() {
    private  val viewModel: WCCAvailableVM by viewModels {
        ViewModelFactory(WCCAvailableUseCase(WCCryptoRepositoryImp(retrofit)))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getAvailableBook()
    }

}