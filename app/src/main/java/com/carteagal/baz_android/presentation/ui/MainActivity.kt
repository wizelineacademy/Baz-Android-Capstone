package com.carteagal.baz_android.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carteagal.baz_android.R
import com.carteagal.baz_android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}