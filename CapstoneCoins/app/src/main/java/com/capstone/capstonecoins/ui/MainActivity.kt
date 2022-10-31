package com.capstone.capstonecoins.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.RemoteModule
import com.capstone.capstonecoins.domain.api.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var rf: RemoteModule
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rf = RemoteModule()
       //getAvailableBooks()
        getTicker()
       // getOrderBooks()
    }

    private fun getAvailableBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = rf.getRetrofit().create(ApiService::class.java).getAvailableBooks()
                val books = call.payload
                if (call.success) {
                    Log.d("Mensaje", "books: $books")
                } else {
                    Log.d("Mensaje", "anuma :,O")
                }
            } catch (e: Exception) {
                Log.d("Mensaje", "$e")
            }
        }
    }

    private fun getTicker() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = rf.getRetrofit().create(ApiService::class.java)
                    .getTicker("btc_mxn")
                val ticker = call.payload
                if (call.success) {
                    Log.d("Mensaje", "books: $ticker")
                } else {
                    Log.d("Mensaje", "anuma :,O")
                }
            } catch (e: Exception) {
                Log.d("Mensaje", "$e")
            }
        }
    }

    private fun getOrderBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = rf.getRetrofit().create(ApiService::class.java)
                    .getOrderBooks("btc_mxn")
                val ticker = call.payload
                if (call.success) {
                    Log.d("Mensaje", "books: $ticker")
                } else {
                    Log.d("Mensaje", "anuma :,O")
                }
            } catch (e: Exception) {
                Log.d("Mensaje", "$e")
            }
        }
    }


}