package com.example.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AskbidItemBinding
import com.example.myapplication.model.Ask
import com.example.myapplication.model.Bid

/**
 * Created by: Juan Antonio Amado
 * date: 21,septiembre,2022
 */
class AskBidAdapter2 : RecyclerView.Adapter<AskBidAdapter2.CriptoViewHolder>() {

    var result: ArrayList<Ask>  = ArrayList()
    var resultbid: ArrayList<Bid>  = ArrayList()
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AskBidAdapter2.CriptoViewHolder {
        val binding: AskbidItemBinding = AskbidItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CriptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriptoViewHolder, position: Int) {
        val item = result[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return result.size
    }


    inner class CriptoViewHolder(private val binding: AskbidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ask) {
            binding.askName.text = item.price
            binding.bidName.text = resultbid[adapterPosition].price
        }
    }

    fun addData(listItems: ArrayList<Ask>) {
        val size = this.result.size
        this.result.addAll(listItems)
        result.add(0,Ask("0","0","ASK"))
        val sizeNew = this.result.size
        notifyItemRangeChanged(size, sizeNew)
    }
    fun addDataBids(listItems: ArrayList<Bid>) {
        val size = this.result.size
        this.resultbid.addAll(listItems)
        resultbid.add(0,Bid("0","0","BID"))
        val sizeNew = this.result.size
        notifyItemRangeChanged(size, sizeNew)
    }
}