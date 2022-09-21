package com.example.capstoneproject.ui.adapter.orderBook.ask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.orderBook.AskModel
import com.example.capstoneproject.ui.adapter.orderBook.OrderBookViewHolder

class AskAdapter: ListAdapter<AskModel, OrderBookViewHolder>(DiffUtilCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_order_book,parent,false)
        return OrderBookViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: OrderBookViewHolder, position: Int) {
        holder.render(item = getItem(position))
    }
}

private object DiffUtilCallback : DiffUtil.ItemCallback<AskModel>() {
    override fun areItemsTheSame(oldItem: AskModel, newItem: AskModel): Boolean = oldItem.price == newItem.price || oldItem.amount == newItem.amount
    override fun areContentsTheSame(oldItem: AskModel, newItem: AskModel): Boolean = oldItem == newItem

}