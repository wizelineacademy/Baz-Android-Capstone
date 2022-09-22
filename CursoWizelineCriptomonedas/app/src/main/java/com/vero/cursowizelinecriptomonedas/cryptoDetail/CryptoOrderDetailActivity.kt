package com.vero.cursowizelinecriptomonedas.cryptoDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vero.cursowizelinecriptomonedas.model.Crypto
import com.vero.cursowizelinecriptomonedas.R
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.databinding.ActivityCryptoDetailBinding
import com.vero.cursowizelinecriptomonedas.model.CryptoOrder

class CryptoOrderDetailActivity : AppCompatActivity() {
    companion object {
        //Key
        const val CRYPTO_KEY = "crypto"
    }

    private val cryptoOrderListViewModel: CryptoOrderListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCryptoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val crypto = intent?.extras?.getParcelable<Crypto>(CRYPTO_KEY)

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

        cryptoOrderList(crypto.book)

        /***Recycler***/
        val loadingWheel = binding.loadingWheel
        val recycler = binding.cryptoOrderRecycler
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = CryptoOrderAdapter()

        recycler.adapter = adapter
        //Observer
        cryptoOrderListViewModel.cryptoOrderList.observe(this) { cryptoOrderList: List<CryptoOrder> ->
            adapter.submitList(cryptoOrderList)
        }
        cryptoOrderListViewModel.status.observe(this) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, status.messageId, Toast.LENGTH_SHORT).show()
                }
                is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }
    }

    fun cryptoOrderList(crypto: String) {
        cryptoOrderListViewModel.downloadCryptoOrder(crypto)
    }
}