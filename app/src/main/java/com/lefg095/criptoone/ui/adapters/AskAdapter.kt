package com.lefg095.criptoone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lefg095.criptoone.R
import com.lefg095.criptoone.databinding.ItemOrderBinding
import com.lefg095.criptoone.domain.Ask
import java.text.NumberFormat
import java.util.*

class AskAdapter(
    val itemList: List<Ask>
) : RecyclerView.Adapter<AskAdapter.AskViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  AskViewHolder(layoutInflater.inflate(R.layout.item_order, parent, false))
    }

    override fun onBindViewHolder(holder: AskViewHolder, i: Int) {
        holder.bind(itemList[i])
    }

    override fun getItemCount(): Int = itemList.size

    class AskViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemOrderBinding.bind(view)
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("es", "US"))
        fun bind(ask: Ask){
            binding.tvAmount.text = ask.amount
            binding.tvPrice.text = numberFormat.format(ask.price.toFloat())
        }
    }

}
