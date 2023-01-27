package com.baz.cmv.criptomonedas.coins.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baz.cmv.criptomonedas.coins.Coins
import com.baz.cmv.criptomonedas.coins.ui.CriptoListAdapter.CriptoViewHolder
import com.baz.cmv.criptomonedas.databinding.ItemCriptomonedaBinding

class CriptoListAdapter(private val onClick: (Coins) -> Unit) : ListAdapter<Coins, CriptoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Coins>(){
        override fun areItemsTheSame(oldItem: Coins, newItem: Coins): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Coins, newItem: Coins): Boolean {
            return oldItem.criptomoneda == newItem.criptomoneda
        }
    }

    inner class CriptoViewHolder(private val binding : ItemCriptomonedaBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coins: Coins){
            binding.root.setOnClickListener{
                onClick(coins)
            }
            binding.cryptoOrderBook.text = coins.criptomoneda
            binding.cryptoOrderPrice.text = coins.precio.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriptoViewHolder {
        val binding = ItemCriptomonedaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CriptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriptoViewHolder, position: Int) {
        val coinOrder = getItem(position)
        holder.bind(coinOrder)
    }
}