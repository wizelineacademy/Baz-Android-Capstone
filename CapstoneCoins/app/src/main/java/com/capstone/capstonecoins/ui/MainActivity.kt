package com.capstone.capstonecoins.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.data.repository.CoinsRepositoryImpl
import com.capstone.capstonecoins.data.retrofit
import com.capstone.capstonecoins.ui.adapters.CoinsAdapter
import com.capstone.capstonecoins.ui.listeners.ListenerAdapter
import com.capstone.capstonecoins.ui.viewmodels.CoinViewmodel
import com.capstone.capstonecoins.databinding.ActivityMainBinding
import com.capstone.capstonecoins.domain.api.usecases.AvailableBooksUseCase
import com.capstone.capstonecoins.ui.viewmodels.ViewModelFactory


class MainActivity : AppCompatActivity(), ListenerAdapter {
    lateinit var adapter: CoinsAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    private val coinViewModel: CoinViewmodel by viewModels {
        ViewModelFactory(AvailableBooksUseCase(CoinsRepositoryImpl(retrofit)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_coins)

        callServices()
        attachObservers()

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

    private fun attachObservers() {
        coinViewModel.cryptoBook.observe(this) {
            callDetail(it)
            Log.d("Mensaje", "Anuma $it")
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun callDetail(call: BooksDto) {
        adapter = CoinsAdapter(call.payload, this)
        Log.d("Mensaje", "Show: $call")
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun callServices() {
        coinViewModel.getAvailableBooks()
    }

    /*  private fun getTicker() {
          CoroutineScope(Dispatchers.IO).launch {
              try {
                  val call = rf.getRetrofit().create(ApiService::class.java)
                      .getTicker("btc_mxn")
                  val ticker = call.payload
                  if (call.success) {
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
                  }
              } catch (e: Exception) {
              }
          }
      }*/

    override fun listener() {
        Log.d("Mensaje", "CLICK")
    }


}