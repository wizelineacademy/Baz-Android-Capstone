package com.example.wizelineproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.wizelineproject.domain.model.Book
import com.example.wizelineproject.domain.model.Ticker
import com.example.wizelineproject.domain.repository.CriptosRepository
import com.example.wizelineproject.domain.repository.GenericRepository
import com.example.wizelineproject.domain.service.CriptomonedasServices
import com.example.wizelineproject.viewmodel.AsksViewModel
import com.example.wizelineproject.viewmodel.BidsViewModel
import com.example.wizelineproject.viewmodel.CoinsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vModel: CoinsListViewModel by viewModels()
    private val vModelA: AsksViewModel by viewModels()
    private val vModelB: BidsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vModelA.asks.observe(this){
            it?.forEach {
                Log.e("log", "transaction: "+it.toString())
            }
        }
        vModel.monedas.observe(this){
            it.forEach {
                Log.e("log", "book: "+it.book)
            }
        }
        vModel.ticker.observe(this){
            Log.e("log", "ticker: "+it.toString())
        }
        vModelA.getAsks("btc_mxn")
        vModel.getBooks()
        vModel.getTicker("btc_mxn")

    }
}