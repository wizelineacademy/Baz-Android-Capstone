package com.ari.coins.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.ari.coins.R
import com.ari.coins.data.models.Result
import com.ari.coins.databinding.ActivityCoinsBinding
import com.ari.coins.ui.viewModels.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsActivity : AppCompatActivity() {

    private var _binding: ActivityCoinsBinding? = null
    private val binding: ActivityCoinsBinding get() = _binding!!

    // Init ViewModel here for get the same instance on fragments
    private val coinsViewModel: CoinsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}