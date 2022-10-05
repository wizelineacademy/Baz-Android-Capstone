package com.ari.coins.ui.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ari.coins.R
import com.ari.coins.databinding.ItemCoinBinding
import com.ari.coins.ui.uiModels.AvailableBook

/**
 * @author Ari Valencia
 * @file CoinsAdapter
 * @description ListAdapter for represent coins list
 */

class CoinsAdapter(
    val getCoinUrlImage: (book: String) -> String,
    val onClickCoin: (AvailableBook) -> Unit
) : ListAdapter<AvailableBook, CoinsAdapter.ViewHolder>(AvailableBookCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

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
            binding.tvMaximumPrice.text = String.format("%.2f", coin.maximumPrice.toDouble())
            binding.tvMinimumPrice.text = String.format("%.2f", coin.minimumPrice.toDouble())
        }
    }
}

private object AvailableBookCallBack : DiffUtil.ItemCallback<AvailableBook>() {
    override fun areItemsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean {
        return oldItem.book == newItem.book
    }

    override fun areContentsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean {
        return oldItem == newItem
    }
}
