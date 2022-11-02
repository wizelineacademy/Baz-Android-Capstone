package com.example.cryptocurrencyapp.presentation.view.adapters


import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import com.example.cryptocurrencyapp.databinding.CryptoItemBinding
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.utils.Utils


class WCCryptoAdapter(private val click: (WCCryptoBookDTO) -> Unit) :
    ListAdapter<WCCryptoBookDTO, WCCryptoAdapter.ViewHolder>(CoinDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = getItem(position)
        holder.bind(coin)
        holder.itemView.setOnClickListener {
            click.invoke(coin)
        }
    }

    inner class ViewHolder(private val binding: CryptoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: WCCryptoBookDTO) {
            with(binding) {
                val name = Utils.cleanString(coin.book)
                imgCrypto.setImageResource(coin.logo)
                txtMx.text = name
                txtCryptoName.text = coin.name
                txtMinPrice.text = coin.minPrice
                txtMaxPrice.text = coin.maxPrice

            }
        }
    }

    private object CoinDiffCallback : DiffUtil.ItemCallback<WCCryptoBookDTO>() {
        override fun areItemsTheSame(oldItem: WCCryptoBookDTO, newItem: WCCryptoBookDTO): Boolean =
            oldItem.book == newItem.book

        override fun areContentsTheSame(
            oldItem: WCCryptoBookDTO,
            newItem: WCCryptoBookDTO
        ): Boolean =
            oldItem == newItem
    }

}