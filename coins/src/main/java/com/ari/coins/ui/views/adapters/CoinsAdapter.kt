package com.ari.coins.ui.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ari.coins.R
import com.ari.coins.databinding.ItemCoinBinding
import com.ari.coins.ui.uiModels.AvailableBook

class CoinsAdapter(
    val getCoinUrlImage: (book: String) -> String,
    val onClickCoin: (AvailableBook) -> Unit
) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

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

    inner class ViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: AvailableBook) {
            binding.coin = coin
            binding.ivCoin.load(getCoinUrlImage(coin.book)) {
                crossfade(true)
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_baseline_image_24)
                error(R.drawable.ic_baseline_broken_image_24)
            }
            binding.root.setOnClickListener { onClickCoin(coin) }
        }
    }
}