package com.axiasoft.android.zerocoins.ui.features.availableBooks.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.OpenOrder

class OpenOrdersInBookAdapter : ListAdapter<OpenOrder, OpenOrdersInBookAdapter.UserViewHolder>(UserComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.open_order_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(openOrder: OpenOrder) {
            itemView.findViewById<TextView>(R.id.tv_price).text = openOrder.price
            itemView.findViewById<TextView>(R.id.tv_amount).text = openOrder.amount
            itemView.findViewById<TextView>(R.id.tv_code).text = openOrder.book
        }
    }
}

object UserComparator : DiffUtil.ItemCallback<OpenOrder>() {
    override fun areItemsTheSame(oldItem: OpenOrder, newItem: OpenOrder): Boolean {
        return (oldItem.book == newItem.book) &&
            (oldItem.price == newItem.price) &&
            (oldItem.amount == newItem.amount)
    }

    override fun areContentsTheSame(oldItem: OpenOrder, newItem: OpenOrder): Boolean {
        return (oldItem.book == newItem.book) &&
            (oldItem.price == newItem.price) &&
            (oldItem.amount == newItem.amount)
    }
}
