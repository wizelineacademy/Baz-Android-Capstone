package com.baz.cmv.criptomonedas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baz.cmv.criptomonedas.coins.data.remote.database.CoinsDataBase
import com.baz.cmv.criptomonedas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val noteDatabase by lazy { CoinsDataBase.getDatabase(this).coinDao() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
