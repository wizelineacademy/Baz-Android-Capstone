package com.ari.coins.ui.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ari.coins.databinding.ItemSectionBinding
import com.ari.coins.databinding.ItemStringBinding
import com.ari.coins.databinding.ItemTitleBinding
import com.ari.coins.ui.uiModels.ItemString
import com.ari.coins.ui.uiModels.ItemType

class InfoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_TITLE = 0
        private const val TYPE_INFO = 1
        private const val TYPE_SECTION = 2
    }

    private val list = arrayListOf<ItemString>()

    fun setList(newList: List<ItemString>) {
        list.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TYPE_TITLE -> ViewHolderTitle(ItemTitleBinding.inflate(layoutInflater, parent, false))
            TYPE_SECTION -> ViewHolderSection(ItemSectionBinding.inflate(layoutInflater, parent, false))
            else -> ViewHolderInfo(ItemStringBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is ViewHolderTitle -> holder.bind(list[position])
        is ViewHolderInfo -> holder.bind(list[position])
        is ViewHolderSection -> holder.bind(list[position])
        else -> {}
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = when(list[position].itemType){
        ItemType.TITLE -> TYPE_TITLE
        ItemType.INFO -> TYPE_INFO
        ItemType.SECTION -> TYPE_SECTION
    }

    class ViewHolderTitle(val binding: ItemTitleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemString) {
            binding.item = item
        }
    }

    class ViewHolderInfo(val binding: ItemStringBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemString) {
            binding.item = item
        }
    }

    class ViewHolderSection(val binding: ItemSectionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemString) {
            binding.item = item
        }
    }
}