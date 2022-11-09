package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.BitsoAdapter
import com.example.myapplication.repository.BitsoProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rvBooks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BitsoAdapter(BitsoProvider.bitsoFirst)
    }
}