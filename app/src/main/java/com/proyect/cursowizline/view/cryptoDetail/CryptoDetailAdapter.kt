package com.proyect.cursowizline.view.cryptoDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.proyect.cursowizline.databinding.ListOrderBookCryptoBinding
import com.proyect.cursowizline.model.CryptoOrder

class CryptoDetailAdapter :
    ListAdapter<CryptoOrder, CryptoDetailAdapter.CryptDetailViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<CryptoOrder>() {
        override fun areItemsTheSame(oldItem: CryptoOrder, newItem: CryptoOrder): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoOrder, newItem: CryptoOrder): Boolean {
            return oldItem.book == newItem.book
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptDetailViewHolder {
        val binding = ListOrderBookCryptoBinding.inflate(LayoutInflater.from(parent.context))
        return CryptDetailViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CryptDetailViewHolder, position: Int) {
        val cryptoOrder = getItem(position)
        holder.bind(cryptoOrder)
    }

    inner class CryptDetailViewHolder(private val binding: ListOrderBookCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoOrder: CryptoOrder) {
            binding.cryptoOrderBook.text = cryptoOrder.book
            binding.cryptoOrderPrice.text = cryptoOrder.price
            binding.cryptoOrderAmount.text = cryptoOrder.amount
        }
    }
}