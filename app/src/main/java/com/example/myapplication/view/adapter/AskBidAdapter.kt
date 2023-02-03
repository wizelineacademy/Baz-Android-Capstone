package com.example.myapplication.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AskbidItemBinding
import com.example.myapplication.model.PayloadAskAndBids

/**
 * Created by: Juan Antonio Amado
 * date: 21,septiembre,2022
 */
class AskBidAdapter : ListAdapter<PayloadAskAndBids, AskBidAdapter.CriptoViewHolder>(StockDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriptoViewHolder {
        val binding: AskbidItemBinding = AskbidItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CriptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriptoViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class CriptoViewHolder(private val binding: AskbidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PayloadAskAndBids) {
            item.bids.forEach {
                binding.bidName.text = it.price
            }
        }
    }


    object StockDiffCallback : DiffUtil.ItemCallback<PayloadAskAndBids>() {
        override fun areContentsTheSame(oldItem: PayloadAskAndBids, newItem: PayloadAskAndBids): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: PayloadAskAndBids, newItem: PayloadAskAndBids): Boolean {
            return oldItem == newItem
        }

    }
}