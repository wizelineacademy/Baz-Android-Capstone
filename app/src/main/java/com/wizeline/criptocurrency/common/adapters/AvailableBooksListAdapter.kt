package com.wizeline.criptocurrency.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.criptocurrency.R
import com.wizeline.criptocurrency.common.adapters.utilities.getCoinImage
import com.wizeline.criptocurrency.common.adapters.utilities.getCoinName
import com.wizeline.criptocurrency.databinding.ItemAvailableBookBinding
import com.wizeline.criptocurrency.domain.model.AvailableBook

class AvailableBooksListAdapter(
    private var data: List<AvailableBook> = emptyList(),
    private val goToDetail: (availableBook: AvailableBook?, coinName: String) -> Unit,
) : ListAdapter<AvailableBook, AvailableBooksListAdapter.ViewHolder>(difCallback) {

    companion object {
        val difCallback = object : DiffUtil.ItemCallback<AvailableBook>() {
            override fun areItemsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean {
                return oldItem.book == newItem.book
            }

            override fun areContentsTheSame(
                oldItem: AvailableBook,
                newItem: AvailableBook
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAvailableBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context: Context = holder.itemView.context
        holder.bindItem(getItem(position), context)
    }

    inner class ViewHolder(private val itemBinding: ItemAvailableBookBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private var currentBook: AvailableBook? = null
        private var coinName: String = ""

        init {
            itemView.setOnClickListener {
                goToDetail(currentBook, coinName)
            }
        }

        fun bindItem(item: AvailableBook, context: Context) {
            currentBook = item
            coinName = getCoinName(item.book.toString())
            itemBinding.apply {
                tvCoinName.text = coinName
                tvValueMin.text = context.getString(R.string.min, item.minimum_value)
                tvValueMax.text = context.getString(R.string.max, item.maximum_value)
                imgCoin.setImageDrawable(
                    ContextCompat.getDrawable(
                        context.applicationContext,
                        getCoinImage(item.book.orEmpty())
                    )
                )
            }
        }
    }
}
