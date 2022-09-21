package com.vero.cursowizelinecriptomonedas.cryptoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vero.cursowizelinecriptomonedas.Crypto
import com.vero.cursowizelinecriptomonedas.databinding.CryptoListItemBinding

class CryptoAdapter : ListAdapter<Crypto, CryptoAdapter.CryptoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Crypto>() {
        override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem.book == newItem.book
        }
    }

    //Lambda - click
    private var onItemClickListener: ((Crypto) -> Unit)? = null

    //Recibe lambda
    fun setOnItemClickListener(onItemClickListener: (Crypto) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = CryptoListItemBinding
            .inflate(LayoutInflater.from(parent.context))
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(cryptoViewHolder: CryptoViewHolder, position: Int) {
        val crypto = getItem(position)
        cryptoViewHolder.bind(crypto)
    }

    inner class CryptoViewHolder(private val binding: CryptoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crypto: Crypto) {
            binding.cryptoBook.text = crypto.book
            //Click book
            binding.cryptoBook.setOnClickListener {
                //Si no es null invoke
                onItemClickListener?.invoke(crypto)
            }
        }
    }
}