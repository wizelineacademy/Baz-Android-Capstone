package com.ari.coins.ui.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ari.coins.databinding.ItemSectionBinding
import com.ari.coins.databinding.ItemStringBinding
import com.ari.coins.databinding.ItemTitleBinding
import com.ari.coins.ui.uiModels.ItemString
import com.ari.coins.ui.uiModels.ItemType

/**
 * @author Ari Valencia
 * @file InfoAdapter
 * @description ListAdapter for represent ask and bids of coin
 */

class InfoAdapter : ListAdapter<ItemString, RecyclerView.ViewHolder>(ItemStringCallBack) {

    companion object {
        private const val TYPE_TITLE = 0
        private const val TYPE_INFO = 1
        private const val TYPE_SECTION = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TITLE -> ViewHolderTitle(ItemTitleBinding.inflate(layoutInflater, parent, false))
            TYPE_SECTION -> ViewHolderSection(ItemSectionBinding.inflate(layoutInflater, parent, false))
            else -> ViewHolderInfo(ItemStringBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (holder) {
        is ViewHolderTitle -> holder.bind(getItem(position))
        is ViewHolderInfo -> holder.bind(getItem(position))
        is ViewHolderSection -> holder.bind(getItem(position))
        else -> {}
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position).itemType) {
        ItemType.TITLE -> TYPE_TITLE
        ItemType.INFO -> TYPE_INFO
        ItemType.SECTION -> TYPE_SECTION
    }

    class ViewHolderTitle(val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemString) {
            binding.item = item
        }
    }

    class ViewHolderInfo(val binding: ItemStringBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemString) {
            binding.item = item
            binding.tvValue.text = String.format("%.2f", item.right.toDouble())
        }
    }

    class ViewHolderSection(val binding: ItemSectionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemString) {
            binding.item = item
        }
    }
}

private object ItemStringCallBack : DiffUtil.ItemCallback<ItemString>() {
    override fun areItemsTheSame(oldItem: ItemString, newItem: ItemString): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ItemString, newItem: ItemString): Boolean = oldItem == newItem
}
