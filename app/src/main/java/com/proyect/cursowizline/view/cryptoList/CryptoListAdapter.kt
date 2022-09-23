package com.proyect.cursowizline.view.cryptoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.proyect.cursowizline.databinding.ListItemBookCryptoBinding
import com.proyect.cursowizline.model.Crypto

class CryptoListAdapter : ListAdapter<Crypto, CryptoListAdapter.CryptoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Crypto>() {
        override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem.book == newItem.book
        }
    }

    //Lambda
    private var onItemClickListener: ((Crypto) -> Unit)? = null

    //Aqui Recibo la lambda
    fun setOnItemClickListener(onItemClickListener: (Crypto) -> Unit) {
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
        fun bind(crypto: Crypto) {
            binding.cryptoBook.text = crypto.book
            binding.cryptoBook.setOnClickListener {
                onItemClickListener?.invoke(crypto)
            }
        }
    }
}