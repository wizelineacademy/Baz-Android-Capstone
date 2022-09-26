package com.baz.cmv.criptomonedas.coins.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baz.cmv.criptomonedas.R
import com.baz.cmv.criptomonedas.coins.Coins
import com.baz.cmv.criptomonedas.coins.ui.CriptoListAdapter.*
import com.baz.cmv.criptomonedas.databinding.ItemCriptomonedaBinding

class CriptoListAdapter : ListAdapter<Coins, CriptoViewHolder>(DiffCallback) {
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
            binding.textCriptomoneda.text = coins.criptomoneda
            binding.textPrecio.text = coins.precio.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriptoViewHolder {
        val binding = ItemCriptomonedaBinding.inflate(LayoutInflater.from(parent.context))
        return CriptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriptoViewHolder, position: Int) {
        val coinOrder = getItem(position)
        holder.bind(coinOrder)
    }
}