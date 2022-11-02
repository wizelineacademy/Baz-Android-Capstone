package wizeline.crypto.currency.ui.informationTrading.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wizeline.crypto.currency.R
import wizeline.crypto.currency.databinding.AsksBidsAdapterBinding
import wizeline.crypto.currency.domain.model.AsksBidsModel


class OrderBookAdapter():ListAdapter<AsksBidsModel, OrderBookAdapter.ViewHolder>(
    DelegateDiffCallBack
) {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view=LayoutInflater.from(parent.context).inflate(R.layout.asks_bids_adapter,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=getItem(position)
        holder.binding.apply {
            txtAmount.text="${context.getString(R.string.amount)}\n${data.amount}"
            textPrice.text="${context.getString(R.string.price)}\n${data.price}"
        }
    }



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding = AsksBidsAdapterBinding .bind(itemView)
    }

    object DelegateDiffCallBack:DiffUtil.ItemCallback<AsksBidsModel>(){
        override fun areItemsTheSame(oldItem: AsksBidsModel, newItem: AsksBidsModel): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: AsksBidsModel, newItem: AsksBidsModel): Boolean {
            return oldItem.book==newItem.book
        }
    }


}
