package com.example.capstoneproject.ui.adapter.orderBook

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.orderBook.AskModel
import com.example.capstoneproject.data.model.orderBook.BidModel
import com.example.capstoneproject.databinding.ItemOrderBookBinding

class OrderBookViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemOrderBookBinding.bind(view)

    fun render(item:Any){
        var price = ""
        var amount = ""

        when(item){
            is BidModel -> {
                price = item.price
                amount = item.amount
            }
            is AskModel -> {
                price = item.price
                amount = item.amount
            }
            else ->{}
        }

        binding.apply {
            tvOrderPrice.text = itemView.context.getString(R.string.tv_order_price,price)
            tvOrderAmount.text = itemView.context.getString(R.string.tv_order_amount,amount)
        }
    }


}
