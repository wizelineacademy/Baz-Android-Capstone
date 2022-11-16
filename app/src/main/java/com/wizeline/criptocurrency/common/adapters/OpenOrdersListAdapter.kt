package com.wizeline.criptocurrency.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.criptocurrency.databinding.ItemOpenOrderBinding
import com.wizeline.criptocurrency.domain.model.OpenOrder

class OpenOrdersListAdapter(
    private var data: List<OpenOrder> = emptyList(),
) : ListAdapter<OpenOrder,OpenOrdersListAdapter.ViewHolder>(difCallback) {

    companion object {
        val difCallback = object : DiffUtil.ItemCallback<OpenOrder>() {
            override fun areItemsTheSame(oldItem: OpenOrder, newItem: OpenOrder): Boolean {
                return oldItem.amount == newItem.amount
            }
            override fun areContentsTheSame(oldItem: OpenOrder, newItem: OpenOrder): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemOpenOrderBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    inner class ViewHolder(private val itemBinding: ItemOpenOrderBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(item: OpenOrder) = with(itemBinding) {
            openOrderPrice.text = item.price.toString()
            openOrderAmount.text = item.amount.toString()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
