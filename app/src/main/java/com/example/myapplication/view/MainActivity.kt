package com.example.myapplication.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Payload
import com.example.myapplication.model.PayloadX
import com.example.myapplication.view.adapter.CritpAdapter
import com.example.myapplication.view.interfaces.OnclickListenerItem
import com.example.myapplication.viewModel.BitsoViewModel

class MainActivity : AppCompatActivity(), OnclickListenerItem{
    private lateinit var viewModel: BitsoViewModel
    lateinit var binding: ActivityMainBinding
    private val criptoAdapter = CritpAdapter(this)
    private var list: ArrayList<Payload>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[BitsoViewModel::class.java]

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = criptoAdapter

        viewModel.getCriptoCurrency().observe(this) {
            if (it != null) {
                it.payload.forEach {
                    if (it.book.contains("mxn")) {
                        list?.addAll(listOf(it))
                    }
                }
                updateAdapter()
            }
        }

        initWS()
    }

    private fun initWS() {
        viewModel.consultCriptoCurrency()
    }

    fun updateAdapter() {
        criptoAdapter.submitList(list)


    }

    override fun onCellClickListener(data: Payload) {
        Log.e("ok", "{${data.book}}")
        Log.e("ok", "{${data.maximum_amount}}")
        Toast.makeText(this,"Cell clicked", Toast.LENGTH_SHORT).show()
    }

}