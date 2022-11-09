package com.example.myapplication.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.SimpleBook

class BitsoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val simpleBook = view.findViewById<TextView>(R.id.tv_coin_name)
    val maxPrice = view.findViewById<TextView>(R.id.tv_max_price)
    val minPrice = view.findViewById<TextView>(R.id.tv_min_price)

    fun render(simpleBookModel: SimpleBook){

        simpleBook.text = simpleBookModel.bookName
        maxPrice.text = simpleBookModel.maxPrice
        minPrice.text = simpleBookModel.minimumPrice
    }
}