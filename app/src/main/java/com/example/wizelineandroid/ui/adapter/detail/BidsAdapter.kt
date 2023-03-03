package com.example.wizelineandroid.ui.adapter.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wizelineandroid.core.BaseViewHolder
import com.example.wizelineandroid.data.local.entitys.BidsEntity
import com.example.wizelineandroid.databinding.ItemOrderBooksBinding

class BidsAdapter(private val orderList: List<BidsEntity>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemOrderBooksBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrderViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is OrderViewHolder -> holder.bind(orderList[position])
        }
    }

    override fun getItemCount(): Int = orderList.size

    inner class OrderViewHolder(private val binding: ItemOrderBooksBinding) :
        BaseViewHolder<BidsEntity>(
            binding.root
        ) {
        override fun bind(item: BidsEntity) {
            binding.book.text = item.book
            binding.price.text = item.price
            binding.amount.text = item.amount
        }
    }
}
