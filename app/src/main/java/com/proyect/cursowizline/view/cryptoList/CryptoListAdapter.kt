package com.proyect.cursowizline.view.cryptoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.proyect.cursowizline.databinding.ListItemBookCryptoBinding
import com.proyect.cursowizline.domain.model.CryptoM

class CryptoListAdapter : ListAdapter<CryptoM, CryptoListAdapter.CryptoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CryptoM>() {
        override fun areItemsTheSame(oldItem: CryptoM, newItem: CryptoM): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoM, newItem: CryptoM): Boolean {
            return oldItem.book == newItem.book
        }
    }

    private var onItemClickListener: ((CryptoM) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (CryptoM) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = ListItemBookCryptoBinding.inflate(LayoutInflater.from(parent.context))
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(cryptoViewHolder: CryptoViewHolder, position: Int) {
        val crypto = getItem(position)
        cryptoViewHolder.bind(crypto)
    }

    inner class CryptoViewHolder(private val binding: ListItemBookCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crypto: CryptoM) {
            binding.cryptoBook.text = crypto.book
            binding.cryptoBook.setOnClickListener {
                onItemClickListener?.invoke(crypto)
            }
        }
    }
}