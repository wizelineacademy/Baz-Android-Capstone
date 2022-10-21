package com.lefg095.criptoone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lefg095.criptoone.R
import com.lefg095.criptoone.databinding.ItemOrderBinding
import com.lefg095.criptoone.domain.Bid
import java.text.NumberFormat
import java.util.*

class BidAdapter(
    val itemList: List<Bid>
) : RecyclerView.Adapter<BidAdapter.BidViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BidViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  BidViewHolder(layoutInflater.inflate(R.layout.item_order, parent, false))
    }

    override fun onBindViewHolder(holder: BidViewHolder, i: Int) {
        holder.bind(itemList[i])
    }

    override fun getItemCount(): Int = itemList.size

    class BidViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemOrderBinding.bind(view)
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("es", "US"))
        fun bind(bid: Bid){

            binding.tvAmount.text = bid.amount
            binding.tvPrice.text = numberFormat.format(bid.price.toFloat())
        }
    }

}