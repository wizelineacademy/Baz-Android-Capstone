package com.javg.cryptocurrencies.ui.detail.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import com.javg.cryptocurrencies.databinding.CryAskItemBinding
import okhttp3.internal.notifyAll

class CRYAskRecyclerView(
    private val listAsk: MutableList<CRYAskOrBids>,
    private val context: Context): RecyclerView.Adapter<CRYAskRecyclerView.CRYAskViewHolder>(){

    inner class CRYAskViewHolder(private val binding: CryAskItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(askOrBids: CRYAskOrBids){
            binding.edPrice.text = String.format(context.getString(R.string.cry_price_order), askOrBids.price)
            binding.edAmount.text = askOrBids.amount
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CRYAskViewHolder {
        val binding = CryAskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CRYAskViewHolder(binding)
    }

    override fun getItemCount() = listAsk.size

    override fun onBindViewHolder(holder: CRYAskViewHolder, position: Int) {
        holder.bind(listAsk[position])
    }

    fun update(list: MutableList<CRYAskOrBids>){
        listAsk.clear()
        listAsk.addAll(list)
        notifyDataSetChanged()
    }
}