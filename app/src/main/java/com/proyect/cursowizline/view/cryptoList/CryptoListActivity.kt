package com.proyect.cursowizline.view.cryptoList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyect.cursowizline.databinding.ListCryptoBinding
import com.proyect.cursowizline.model.ResponseStatus
import com.proyect.cursowizline.view.cryptoDetail.CryptoDetailActivity
import com.proyect.cursowizline.view.cryptoDetail.CryptoDetailActivity.Companion.CRYPTO_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoListActivity : AppCompatActivity() {

    private val cryptoListViewModel: CryptoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ListCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingWheel = binding.loadingWheel

        val recycler = binding.cryptoRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = CryptoListAdapter()

        adapter.setOnItemClickListener { crypto ->
            val intent = Intent(this, CryptoDetailActivity::class.java)
            intent.putExtra(CRYPTO_KEY, crypto)
            startActivity(intent)
        }

        recycler.adapter = adapter

        cryptoListViewModel.cryptoList.observe(this) { cryptoList ->
            adapter.submitList(cryptoList)
        }

        cryptoListViewModel.status.observe(this) { status ->
            when (status) {
                is ResponseStatus.Error -> {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, status.messageId, Toast.LENGTH_SHORT).show()
                }
                is ResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }
    }
}