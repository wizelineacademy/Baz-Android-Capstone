package com.capstone.capstonecoins.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.models.availablebooks.Payload
import com.capstone.capstonecoins.ui.listeners.ListenerAdapter


class CoinsAdapter(private var dataSet: List<Payload>, var setListener: ListenerAdapter) :
    RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    class ViewHolder(view: View, setListener: ListenerAdapter) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.tv_book)
            textView.setOnClickListener {
                setListener.listener()
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_coin, viewGroup, false)
        return ViewHolder(view, setListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].book
    }

    override fun getItemCount() = dataSet.size

}
