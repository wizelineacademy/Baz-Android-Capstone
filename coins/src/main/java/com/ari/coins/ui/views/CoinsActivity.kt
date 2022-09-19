package com.ari.coins.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.ari.coins.R
import com.ari.coins.data.models.Result
import com.ari.coins.ui.viewModels.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsActivity : AppCompatActivity() {

    private val coinsViewModel: CoinsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coins)

        addObservers()
        coinsViewModel.getAvailableBooks()

    }

    private fun addObservers() {
        coinsViewModel.availableBooks.observe(this) { result ->
            when(result) {
                is Result.Error -> {
                    Log.e("AVD", "${result.code} - ${result.message}")
                }
                is Result.Success -> {
                    Log.e("AVD", result.data.toString())
                    coinsViewModel.getTicker(result.data[0].book)
                    coinsViewModel.getOrderBook(result.data[0].book)
                }
            }
        }

        coinsViewModel.ticker.observe(this) { result ->
            when(result) {
                is Result.Error -> {
                    Log.e("TICKER", "${result.code} - ${result.message}")
                }
                is Result.Success -> {
                    Log.e("TICKER", result.data.toString())
                }
            }
        }

        coinsViewModel.orderBook.observe(this) { result ->
            when(result) {
                is Result.Error -> {
                    Log.e("ORDERBOOK", "${result.code} - ${result.message}")
                }
                is Result.Success -> {
                    Log.e("ORDERBOOK", result.data.toString())
                }
            }
        }
    }
}