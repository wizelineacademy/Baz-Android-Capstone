package com.proyect.cursowizline.view.cryptoDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyect.cursowizline.R
import com.proyect.cursowizline.databinding.DetailCryptoBinding
import com.proyect.cursowizline.domain.model.CryptoM
import com.proyect.cursowizline.model.Crypto
import com.proyect.cursowizline.model.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoDetailActivity: AppCompatActivity() {
    companion object {
        const val CRYPTO_KEY = "crypto"
    }

    private val cryptoOrderListViewModel: CryptoDetailListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =  DetailCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val crypto = intent?.extras?.getParcelable<CryptoM>(CRYPTO_KEY)

        if (crypto == null) {
            Toast.makeText(this, R.string.error_showing_crypto_not_found, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.cryptoBook.text = crypto.book
        binding.minimumPrice.text = crypto.minimum_price
        binding.maximumPrice.text = crypto.maximum_price
        binding.ticketSize.text = crypto.tick_size
        binding.minimumValue.text = crypto.minimum_value
        binding.maximumValue.text = crypto.maximum_value
        binding.crypto = crypto
        binding.closeButton.setOnClickListener {
            finish()
        }

        val loadingWheel = binding.loadingWheel
        val recycler = binding.cryptoOrderRecycler
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = CryptoDetailAdapter()

        recycler.adapter = adapter

        cryptoOrderListViewModel.cryptoOrderList.observe(this) { cryptoOrderList ->
            adapter.submitList(cryptoOrderList)
        }
        cryptoOrderListViewModel.status.observe(this) { status ->
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