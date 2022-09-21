package com.vero.cursowizelinecriptomonedas.cryptoList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoDetailActivity
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoDetailActivity.Companion.CRYPTO_KEY
import com.vero.cursowizelinecriptomonedas.databinding.ActivityCryptoListBinding

class CryptoListActivity : AppCompatActivity() {
    //Instance del ViewModel
    private val cryptoListViewModel: CryptoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCryptoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingWheel = binding.loadingWheel

        val recycler = binding.cryptoRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = CryptoAdapter()

        adapter.setOnItemClickListener { crypto ->
            //Put extra Crypto to CryptoDetailActivity
            val intent = Intent(this, CryptoDetailActivity::class.java)
            intent.putExtra(CRYPTO_KEY, crypto)
            startActivity(intent)
        }

        recycler.adapter = adapter

        //Observer
        cryptoListViewModel.cryptoList.observe(this) { cryptoList ->
            adapter.submitList(cryptoList)
        }
        //Observe Status
        cryptoListViewModel.status.observe(this) { status ->
            when (status) {
                ApiResponseStatus.LOADING -> {
                    loadingWheel.visibility = View.VISIBLE
                }
                ApiResponseStatus.ERROR -> {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, "Error to show data", Toast.LENGTH_SHORT).show()
                }
                ApiResponseStatus.SUCCESS -> {
                    loadingWheel.visibility = View.GONE
                }
                else -> {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, "Error unknown", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}