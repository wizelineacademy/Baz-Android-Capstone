package com.example.bazandroidcourse.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bazandroidcourse.data.entities.BookOrderResumeModel
import com.example.bazandroidcourse.databinding.BookOrderItemLayoutBinding

class BookOrdersAdapter ():
    ListAdapter<BookOrderResumeModel, BookOrdersAdapter.ItemViewHolder> (DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            BookOrderItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder:BookOrdersAdapter.ItemViewHolder,
        position:Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(
        private val binding: BookOrderItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:BookOrderResumeModel) = with(binding) {
            tvAmount.text = item.amount
            tvPrice.text  = item.price
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<BookOrderResumeModel>() {
        override fun areItemsTheSame(oldItem: BookOrderResumeModel, newItem: BookOrderResumeModel) = oldItem.book == newItem.book
        override fun areContentsTheSame(oldItem: BookOrderResumeModel, newItem: BookOrderResumeModel) = oldItem == newItem

    }
}

