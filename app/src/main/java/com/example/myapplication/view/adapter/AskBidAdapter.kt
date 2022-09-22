package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AskbidItemBinding
import com.example.myapplication.model.Payload

/**
 * Created by: Juan Antonio Amado
 * date: 21,septiembre,2022
 */
class AskBidAdapter : ListAdapter<Payload, AskBidAdapter.CriptoViewHolder>(StockDiffCallback) {

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
        fun bind(item: Payload) {

        }
    }


    object StockDiffCallback : DiffUtil.ItemCallback<Payload>() {
        override fun areContentsTheSame(oldItem: Payload, newItem: Payload): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Payload, newItem: Payload): Boolean {
            return oldItem.book == newItem.book
        }
    }
}