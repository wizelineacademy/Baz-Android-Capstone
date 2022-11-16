package com.wizeline.criptocurrency.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.criptocurrency.databinding.ItemOpenOrderBinding
import com.wizeline.criptocurrency.domain.model.OpenOrder

class OpenOrdersAdapter(
    private var data: List<OpenOrder> = emptyList(),
) : RecyclerView.Adapter<OpenOrdersAdapter.ViewHolder>() {

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
