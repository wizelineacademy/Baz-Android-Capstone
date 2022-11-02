package com.example.cryptocurrencyapp.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.databinding.DetailItemBinding
import com.example.cryptocurrencyapp.domain.entity.WCCOrderBookDTO

class OrderAdapter: ListAdapter<WCCOrderBookDTO, OrderAdapter.ViewHolder>(DetalDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detailCoin = getItem(position)
        holder.bind(detailCoin)
    }

    inner class ViewHolder(private val binding: DetailItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(coin :WCCOrderBookDTO){
            with(binding){
                txtCryptoValue.text = coin.book
                txtValueAmount.text = coin.amount
                txtValuePrice.text = coin.price
                txtValueType.text = coin.type
            }
        }
    }
}

 private object DetalDiffCallback : DiffUtil.ItemCallback<WCCOrderBookDTO>() {
    override fun areItemsTheSame(oldItem: WCCOrderBookDTO, newItem: WCCOrderBookDTO): Boolean =
        oldItem.book == newItem.book

    override fun areContentsTheSame(oldItem: WCCOrderBookDTO, newItem: WCCOrderBookDTO): Boolean =
        oldItem == newItem
}