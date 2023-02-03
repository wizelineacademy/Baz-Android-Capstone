package com.jpgl.cryptocurrencies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jpgl.cryptocurrencies.databinding.CryptoBidsItemBinding
import com.jpgl.cryptocurrencies.databinding.CryptoItemBinding
import com.jpgl.cryptocurrencies.domain.model.BidsModelDomain
import com.jpgl.cryptocurrencies.domain.model.BooksModelDomain
import com.jpgl.cryptocurrencies.utils.Utils.toBookName

class BidsAdapter(
): ListAdapter<BidsModelDomain, BidsAdapter.ViewHolder>(difCallback)  {

    companion object{
        var difCallback = object : DiffUtil.ItemCallback<BidsModelDomain>() {
            override fun areItemsTheSame(oldItem: BidsModelDomain, newItem: BidsModelDomain): Boolean =
                oldItem.bookBids == newItem.bookBids
            override fun areContentsTheSame(oldItem: BidsModelDomain, newItem: BidsModelDomain): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val bookBinding = CryptoBidsItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(bookBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.enlazarItem(getItem(position))
    }

    inner class ViewHolder(val binding: CryptoBidsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun enlazarItem( bidsModel: BidsModelDomain){

            binding.txtBookBids.text = bidsModel.bookBids
            binding.txtPriceBids.text = bidsModel.priceBids
            binding.txtAmountBids.text = bidsModel.amountBids

        }
    }

}