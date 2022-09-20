package com.vero.cursowizelinecriptomonedas.cryptoList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vero.cursowizelinecriptomonedas.databinding.ActivityCryptoListBinding

class CryptoListActivity : AppCompatActivity() {
    //Instance del ViewModel
    private val cryptoListViewModel: CryptoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCryptoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler = binding.cryptoRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = CryptoAdapter()
        recycler.adapter = adapter

        //Observer
        cryptoListViewModel.cryptoList.observe(this) { cryptoList ->
            adapter.submitList(cryptoList)
        }
    }
}