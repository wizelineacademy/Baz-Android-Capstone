package com.wizeline.criptocurrency.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.criptocurrency.common.adapters.utilities.getCoinImage
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.databinding.ItemAvailableBookBinding

class AvailableBooksAdapter(
    private var data: List<AvailableBook> = emptyList<AvailableBook>(),
    private val goToDetail: (AvailableBook?) -> Unit,
) : RecyclerView.Adapter<AvailableBooksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAvailableBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context: Context = holder.itemView.context
        holder.bindItem(data[position], context)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val itemBinding: ItemAvailableBookBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(item: AvailableBook, context: Context) {
            itemBinding.apply {
                tvCoinName.text = item.book
                imgCoin.setImageDrawable(ContextCompat.getDrawable(context.applicationContext, getCoinImage(item.book ?: "")))

                itemView.setOnClickListener {
                    goToDetail(item)
                }
            }
        }
    }

}
