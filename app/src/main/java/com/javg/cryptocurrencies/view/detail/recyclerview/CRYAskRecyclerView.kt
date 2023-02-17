package com.javg.cryptocurrencies.view.detail.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import com.javg.cryptocurrencies.databinding.CryAskItemBinding

/**
 * @author Juan Vera Gomez
 * Date modified 08/02/2023
 *
 * Contains the necessary functions to display
 * the ask and bids information
 *
 * @since 2.0
 */
class CRYAskRecyclerView(
    private val context: Context):
    ListAdapter<CRYAskOrBids, CRYAskRecyclerView.CRYAskViewHolder>(AskAndBindDiffCallback()) {

    inner class CRYAskViewHolder(private val binding: CryAskItemBinding): RecyclerView.ViewHolder(binding.root){

        /**
         * Function in charge of setting the information of the ask or bids model that it receives
         */
        fun bind(askOrBids: CRYAskOrBids){
            binding.edPrice.text = String.format(context.getString(R.string.cry_price_order), askOrBids.price)
            binding.edAmount.text = askOrBids.amount
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CRYAskViewHolder {
        val binding = CryAskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CRYAskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CRYAskViewHolder, position: Int) {
        holder.bind(getItem(0))
    }

}

class AskAndBindDiffCallback: DiffUtil.ItemCallback<CRYAskOrBids>(){
    override fun areItemsTheSame(oldItem: CRYAskOrBids, newItem: CRYAskOrBids) = oldItem.book == newItem.book

    override fun areContentsTheSame(oldItem: CRYAskOrBids, newItem: CRYAskOrBids) = oldItem == newItem

}