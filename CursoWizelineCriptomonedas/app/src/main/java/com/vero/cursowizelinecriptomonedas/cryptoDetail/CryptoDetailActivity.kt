package com.vero.cursowizelinecriptomonedas.cryptoDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vero.cursowizelinecriptomonedas.Crypto
import com.vero.cursowizelinecriptomonedas.R
import com.vero.cursowizelinecriptomonedas.databinding.ActivityCryptoDetailBinding

class CryptoDetailActivity : AppCompatActivity() {
    companion object {
        //Key
        const val CRYPTO_KEY = "crypto"
    }

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
    }
}