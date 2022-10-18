package com.example.cryptocurrency_challenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency_challenge.R
import com.example.cryptocurrency_challenge.data.model.PayloadOrderBook

class OrderBookAdapter(private val dataSet: PayloadOrderBook) :
    RecyclerView.Adapter<OrderBookAdapter.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view){

        private val txtBook_asks    : TextView
        private val txtAmount_asks  : TextView
        private val txtPrice_asks   : TextView

        private val txtBook_bids    : TextView
        private val txtAmount_bids  : TextView
        private val txtPrice_bids   : TextView

        init {
            txtBook_asks    = view.findViewById(R.id.book)
            txtAmount_asks  = view.findViewById(R.id.amount)
            txtPrice_asks   = view.findViewById(R.id.price)

            txtBook_bids    = view.findViewById(R.id.book_bids)
            txtAmount_bids  = view.findViewById(R.id.amount_bids)
            txtPrice_bids   = view.findViewById(R.id.price_bids)
        }

        @SuppressLint("SetTextI18n")
        fun linkItem(dataSet: PayloadOrderBook?) {

            if (dataSet != null) {
                txtBook_asks.text = dataSet.asks[adapterPosition].book.replace("_mxn", " ")
                txtAmount_asks.text  = "$${dataSet.asks[adapterPosition].amount}"
                txtPrice_asks.text   = "$${dataSet.asks[adapterPosition].price}"

                txtBook_bids.text = dataSet.bids[adapterPosition].book.replace("_mxn", " ")
                txtAmount_bids.text  = "$${dataSet.bids[adapterPosition].amount}"
                txtPrice_bids.text   = "$${dataSet.bids[adapterPosition].price}"
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