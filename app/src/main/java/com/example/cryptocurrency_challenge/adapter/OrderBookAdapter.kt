package com.example.cryptocurrency_challenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency_challenge.R
import com.example.cryptocurrency_challenge.data.model.PayloadOrderBook
import com.example.cryptocurrency_challenge.databinding.OrderBookItemBinding

class OrderBookAdapter(private val dataSet: PayloadOrderBook) :
    RecyclerView.Adapter<OrderBookAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var binding = OrderBookItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun linkItem(dataSet: PayloadOrderBook?) {

            if (dataSet != null) {
                binding.book.text = dataSet.asks[adapterPosition].book.replace("_mxn", " ")
                binding.amount.text = "$${dataSet.asks[adapterPosition].amount}"
                binding.price.text = "$${dataSet.asks[adapterPosition].price}"

                binding.bookBids.text = dataSet.bids[adapterPosition].book.replace("_mxn", " ")
                binding.amountBids.text = "$${dataSet.bids[adapterPosition].amount}"
                binding.priceBids.text = "$${dataSet.bids[adapterPosition].price}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.linkItem(dataSet)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return dataSet.asks.size
    }

}