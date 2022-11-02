package com.wizeline.criptocurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.wizeline.criptocurrency.common.adapters.OpenOrdersAdapter
import com.wizeline.criptocurrency.common.adapters.RetrofitClient
import com.wizeline.criptocurrency.data.repository.BitsoRepositoryImp
import com.wizeline.criptocurrency.databinding.FragmentBookDetailBinding
import com.wizeline.criptocurrency.domain.model.OpenOrder

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
        criptoCurrencyVM.getTicker(criptoCurrencyVM.selectedOrderBook.value ?: "",
            error = { Toast.makeText(context,it,Toast.LENGTH_SHORT).show()  })
        binding.apply {
            criptoCurrencyVM.isLoading.observe(viewLifecycleOwner) {
                tvBookName.text = criptoCurrencyVM.selectedOrderBook.value
                tvBookLastPrice.text = "Last: ${criptoCurrencyVM.ticker.value?.last ?: ""}"
                tvBookHighPrice.text = "High: ${criptoCurrencyVM.ticker.value?.high ?: ""}"
                tvBookLowPrice.text = "Low: ${criptoCurrencyVM.ticker.value?.low ?: ""}"

                rvOrderAsks.adapter = OpenOrdersAdapter(criptoCurrencyVM.orderBook.value?.asks ?: emptyList())
                rvOrderBids.adapter = OpenOrdersAdapter(criptoCurrencyVM.orderBook.value?.bids ?: emptyList())
            }
        }
    }
}