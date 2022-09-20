package com.ari.coins.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ari.coins.data.models.AvailableBook
import com.ari.coins.databinding.ItemCoinBinding

class CoinsAdapter(val onClickCoin: (AvailableBook) -> Unit): RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    private val coins = arrayListOf<AvailableBook>()

    fun setList(newCoins: List<AvailableBook>) {
        coins.apply {
            clear()
            addAll(newCoins)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(coins[position])

    override fun getItemCount(): Int = coins.size

    inner class ViewHolder(private val binding: ItemCoinBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: AvailableBook) {
            binding.coin = coin
            binding.root.setOnClickListener { onClickCoin(coin) }
        }
    }
}