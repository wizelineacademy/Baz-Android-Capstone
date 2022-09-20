package com.example.capstoneproject.ui.view

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.AvailableBookModel
import com.example.capstoneproject.databinding.ActivityMainBinding
import com.example.capstoneproject.ui.adapter.availableBooks.AvailableBooksAdapter
import com.example.capstoneproject.ui.viewmodel.RecyclerAvailableBooksViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val recyclerAvailableBooksViewModel: RecyclerAvailableBooksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerAvailableBooksViewModel.onCreate()

        setupRecyclerView(binding)
        recyclerAvailableBooksViewModel.isLoading.observe(this){
            binding.progressBarCoins.visibility = it
        }
    }

    private fun setupRecyclerView(binding: ActivityMainBinding) {
        val availableBooksAdapter = AvailableBooksAdapter()
        val layoutManager = LinearLayoutManager(this)
        with(binding) {
            rvAvailableBooks.layoutManager = layoutManager
            rvAvailableBooks.adapter = availableBooksAdapter
        }
        recyclerAvailableBooksViewModel.availableBooks.observe(this) { bookList ->
            availableBooksAdapter.submitList(bookList)
        }
    }
}