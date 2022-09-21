package com.vero.cursowizelinecriptomonedas.cryptoDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vero.cursowizelinecriptomonedas.databinding.ActivityCryptoDetailBinding

class CryptoDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCryptoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}