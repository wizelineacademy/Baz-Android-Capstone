package com.capstone.capstonecoins.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.capstonecoins.data.models.orderbook.Bid
import com.capstone.capstonecoins.databinding.ItemBidBinding

class BidsAdapter(var itemClick: (Bid) -> Unit) :
    ListAdapter<Bid, BidsAdapter.ViewHolder>(comparator) {
    object comparator : DiffUtil.ItemCallback<Bid>() {
        override fun areItemsTheSame(oldItem: Bid, newItem: Bid): Boolean {
            return oldItem.amount == newItem.amount
        }

        override fun areContentsTheSame(oldItem: Bid, newItem: Bid): Boolean {
            return oldItem == newItem
        }

    }

    //Todo Imagenes de CriptoMonedas
    inner class ViewHolder(private val binding: ItemBidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bids: Bid) = with(binding) {
            tvAmount.text = "Amount: " + bids.amount
            //tvBook.text = bids.book
            tvPrice.text = "Price: " + bids.price
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(viewGroup.context)

        val binding = ItemBidBinding.inflate(layoutInflater, viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}