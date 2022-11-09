package com.wizeline.criptocurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.wizeline.criptocurrency.R
import com.wizeline.criptocurrency.common.adapters.OpenOrdersAdapter
import com.wizeline.criptocurrency.databinding.FragmentBookDetailBinding

class OrderBookDetailFragment : Fragment() {

    private val criptoCurrencyVM by activityViewModels<CriptoCurrencyViewModel>()
    private lateinit var binding: FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        criptoCurrencyVM.getTicker(criptoCurrencyVM.selectedOrderBook.value ?: "" )
        binding.apply {
            criptoCurrencyVM.isLoading.observe(viewLifecycleOwner) {
                if(it){
                    //ShowProgress
                }else{
                    //HideProgress
                }
                   }

            criptoCurrencyVM.selectedCoinName.observe(viewLifecycleOwner) {
                tvBookName.text = it
            }

            criptoCurrencyVM.ticker.observe(viewLifecycleOwner) {
                tvBookLastPrice.text = context?.getString(R.string.last,it?.last ?: "")
                tvBookHighPrice.text =  context?.getString(R.string.high,it?.high ?: "")
                tvBookLowPrice.text =  context?.getString(R.string.low,it?.low ?: "")
            }

            criptoCurrencyVM.orderBook.observe(viewLifecycleOwner) {
                rvOrderAsks.adapter = OpenOrdersAdapter(it?.asks ?: emptyList())
                rvOrderBids.adapter = OpenOrdersAdapter(it?.bids ?: emptyList())
            }
        }
    }
}