package com.example.cryptocurrency_challenge
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrency_challenge.databinding.ActivityMainBinding
import com.example.cryptocurrency_challenge.viewmodel.CryptocurrencyViewModel

/*
* Proyecto desarrollado durante entrenamiento en Wizeland
* https://github.com/ccanon28/baz-android-course/tree/main
* Autor:   Carlos Roberto Cano Noguera
* Mentor:  Fabian Jiménez Villegas
* */

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    //private val viewModel: CryptocurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

}