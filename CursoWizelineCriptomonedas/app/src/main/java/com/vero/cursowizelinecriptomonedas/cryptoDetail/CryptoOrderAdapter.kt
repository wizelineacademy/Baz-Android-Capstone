package com.vero.cursowizelinecriptomonedas.cryptoDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vero.cursowizelinecriptomonedas.model.CryptoOrder
import com.vero.cursowizelinecriptomonedas.databinding.CryptoOrderListItemBinding

class CryptoOrderAdapter: ListAdapter<CryptoOrder, CryptoOrderAdapter.CryptoOrderViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<CryptoOrder>() {
        override fun areItemsTheSame(oldItem: CryptoOrder, newItem: CryptoOrder): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoOrder, newItem: CryptoOrder): Boolean {
            return oldItem.book == newItem.book
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoOrderViewHolder {
        val binding = CryptoOrderListItemBinding.inflate(LayoutInflater.from(parent.context))
        return CryptoOrderViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CryptoOrderViewHolder, position: Int) {
        val cryptoOrder = getItem(position)
        holder.bind(cryptoOrder)
    }

    inner class CryptoOrderViewHolder(private val binding: CryptoOrderListItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(cryptoOrder: CryptoOrder){
                binding.cryptoOrderBook.text = cryptoOrder.book
                binding.cryptoOrderPrice.text = cryptoOrder.price
                binding.cryptoOrderAmount.text = cryptoOrder.amount
            }
    }
}