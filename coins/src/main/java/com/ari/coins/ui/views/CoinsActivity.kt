package com.ari.coins.ui.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ari.coins.databinding.ActivityCoinsBinding
import com.ari.coins.ui.viewModels.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Ari Valencia
 * @file CoinsActivity
 * @description Activity container of coin list and detail using navigation component
 */

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
        coinsViewModel.getAvailableBooks()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
