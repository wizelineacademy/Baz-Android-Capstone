package com.example.cryptocurrency_challenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency_challenge.adapter.OrderBookAdapter
import com.example.cryptocurrency_challenge.databinding.FragmentDetailCurrencysBinding
import com.example.cryptocurrency_challenge.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCurrencysFragment : Fragment() {

    private val viewModelTicker: TickerViewModel by viewModels()
    private val viewModelOrderBook: OrderBookViewModel by viewModels()

    private var _binding: FragmentDetailCurrencysBinding? = null
    private val binding get() = _binding!!
    private var currency_name:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle->
            currency_name= bundle.getString("nameclicked")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCurrencysBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            txtCurrencyName.text = currency_name?.uppercase()?.replace("_MXN", " ")
            viewModelTicker.geTicker(currency_name)
            viewModelOrderBook.getOrderBook(currency_name)
            viewModelTicker.isLoading.observe(viewLifecycleOwner, Observer {
                loading.isVisible = it
            })
            viewModelTicker.payLoadTicker.observe(viewLifecycleOwner, Observer { it ->
                "$${it?.payLoadTicker?.last}".also { txtLastPriceAmount.text = it }
                "$${it?.payLoadTicker?.high}".also { txtHighestPriceAmount.text = it }
                "$${it?.payLoadTicker?.low}".also { txtLowestPriceAmount.text = it }
            })
            viewModelOrderBook.orderBookModel.observe(viewLifecycleOwner, Observer {
                val adaptador = OrderBookAdapter(it.askList!!)
                binding.recyclerviewAsks.adapter= adaptador
                binding.recyclerviewAsks.layoutManager= LinearLayoutManager(context)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
