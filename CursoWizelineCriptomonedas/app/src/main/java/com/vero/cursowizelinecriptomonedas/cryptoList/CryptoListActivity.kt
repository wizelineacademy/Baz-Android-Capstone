package com.vero.cursowizelinecriptomonedas.cryptoList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vero.cursowizelinecriptomonedas.Crypto
import com.vero.cursowizelinecriptomonedas.databinding.ActivityCryptoListBinding

class CryptoListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCryptoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cryptoList = getFakeCrypto()

        val recycler = binding.cryptoRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = CryptoAdapter()
        recycler.adapter = adapter

        adapter.submitList(cryptoList)
    }

    private fun getFakeCrypto(): MutableList<Crypto> {
        val cryptoList = mutableListOf<Crypto>()
        cryptoList.add(
            Crypto(1, "btc_mxn", 10.1, 10.9, 10.2, 10.8, 10.3, 10.7, 11.0)
        )
        cryptoList.add(
            Crypto(2, "eth_btc", 12.1, 12.9, 12.2, 12.8, 12.3, 12.7, 13.0)
        )
        cryptoList.add(
            Crypto(3, "eth_mxn", 14.1, 14.9, 14.2, 14.8, 14.3, 14.7, 15.0)
        )
        cryptoList.add(
            Crypto(4, "xrp_btc", 16.1, 16.9, 16.2, 16.8, 16.3, 16.7, 17.0)
        )
        return cryptoList
    }
}