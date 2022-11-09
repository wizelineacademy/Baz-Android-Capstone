package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.SimpleBook
import com.example.myapplication.presentation.BitsoViewHolder

class BitsoAdapter(private val bitsoList: List<SimpleBook>): RecyclerView.Adapter<BitsoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitsoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BitsoViewHolder(layoutInflater.inflate(R.layout.item_bitso, parent, false))
    }

    override fun onBindViewHolder(holder: BitsoViewHolder, position: Int) {
        val item = bitsoList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = bitsoList.size

}