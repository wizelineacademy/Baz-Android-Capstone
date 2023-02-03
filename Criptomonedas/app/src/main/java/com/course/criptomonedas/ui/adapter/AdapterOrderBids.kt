package com.course.criptomonedas.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.R
import com.course.criptomonedas.data.models.Bid
import com.course.criptomonedas.databinding.ItemOrderBinding

class AdapterOrderBids(
) : ListAdapter<Bid, AdapterOrderBids.ViewHolder>(DiffUtilCallbackBid) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemOrderBinding.bind(view)

        fun bind(
            item: Bid,
        ) = with(binding) {
            if (adapterPosition % 2 == 0) {
                itemBook.setBackgroundResource(R.color.gray_cell)
            } else {
                itemBook.setBackgroundResource(R.color.white)
            }

            tvBook.text = item.price
            tvBook2.text = item.amount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffUtilCallbackBid : DiffUtil.ItemCallback<Bid>() {
    override fun areItemsTheSame(oldItem: Bid, newItem: Bid): Boolean {
        return oldItem.price == newItem.price
    }

    override fun areContentsTheSame(oldItem: Bid, newItem: Bid): Boolean {
        return oldItem == newItem
    }
}