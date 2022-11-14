package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.core.BaseViewHolder
import com.example.myapplication.data.model.Book
import com.example.myapplication.databinding.ItemBitsoBinding

class BitsoAdapter(private  val bitsoList: List<Book>,
                   private val itemClickListener: OnBitsoClickListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnBitsoClickListener{
        fun onBitsoClick(book: Book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemBitsoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = BitcoinViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onBitsoClick(bitsoList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is BitcoinViewHolder -> holder.bind(bitsoList[position])
        }
    }

    override fun getItemCount(): Int = bitsoList.size

    private inner class BitcoinViewHolder(
        val binding: ItemBitsoBinding,
        val context: Context
    ): BaseViewHolder<Book>(binding.root){
        override fun bind(item: Book){
            Glide.with(context).load(R.drawable.ic_info)
                .centerCrop().into(binding.ivImageCoin)
            binding.tvCoinName.text = item.book
            binding.tvMaxPrice.text = item.maximumPrice.toString()
            binding.tvMinPrice.text = item.minimumPrice.toString()

            when(item.book){
                "btc_mxn" -> Glide.with(context).load(R.drawable.loopring).centerCrop().into(binding.ivImageCoin)
                "eth_btc" -> Glide.with(context).load(R.drawable.paxgold).centerCrop().into(binding.ivImageCoin)
                "eth_mxn" -> Glide.with(context).load(R.drawable.cardano).centerCrop().into(binding.ivImageCoin)
                "xrp_btc" -> Glide.with(context).load(R.drawable.balancer).centerCrop().into(binding.ivImageCoin)
                "xrp_mxn" -> Glide.with(context).load(R.drawable.paxgold).centerCrop().into(binding.ivImageCoin)
                "ltc_btc" -> Glide.with(context).load(R.drawable.axie).centerCrop().into(binding.ivImageCoin)
                "ltc_mxn" -> Glide.with(context).load(R.drawable.fantom).centerCrop().into(binding.ivImageCoin)
                "bch_btc" -> Glide.with(context).load(R.drawable.gala).centerCrop().into(binding.ivImageCoin)
                "bch_mxn" -> Glide.with(context).load(R.drawable.loopring).centerCrop().into(binding.ivImageCoin)
                "tusd_btc" -> Glide.with(context).load(R.drawable.cardano).centerCrop().into(binding.ivImageCoin)
                "tusd_mxn" -> Glide.with(context).load(R.drawable.loopring).centerCrop().into(binding.ivImageCoin)
                "mana_btc" -> Glide.with(context).load(R.drawable.paxgold).centerCrop().into(binding.ivImageCoin)
                "mana_mxn" -> Glide.with(context).load(R.drawable.cardano).centerCrop().into(binding.ivImageCoin)
                "bat_btc" -> Glide.with(context).load(R.drawable.balancer).centerCrop().into(binding.ivImageCoin)
                "bat_mxn" -> Glide.with(context).load(R.drawable.paxgold).centerCrop().into(binding.ivImageCoin)
                "btc_ars" -> Glide.with(context).load(R.drawable.axie).centerCrop().into(binding.ivImageCoin)
                "btc_dai" -> Glide.with(context).load(R.drawable.fantom).centerCrop().into(binding.ivImageCoin)
                "dai_mxn" -> Glide.with(context).load(R.drawable.gala).centerCrop().into(binding.ivImageCoin)
                "btc_usd" -> Glide.with(context).load(R.drawable.loopring).centerCrop().into(binding.ivImageCoin)
                "xrp_usd" -> Glide.with(context).load(R.drawable.cardano).centerCrop().into(binding.ivImageCoin)

            }
        }
    }

}


