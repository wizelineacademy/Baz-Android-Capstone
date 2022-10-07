package com.example.cryptocurrency_challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrency_challenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/*
* Capstone Proyect
* Desarrollado durante entrenamiento en Wizeland Academy
* https://github.com/ccanon28/baz-android-course/tree/main
* Autor:   Carlos Roberto Cano Noguera
* Mentor:  Fabian Jiménez Villegas
* */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}