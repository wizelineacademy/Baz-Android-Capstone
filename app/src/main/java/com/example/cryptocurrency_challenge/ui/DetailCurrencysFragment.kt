package com.example.cryptocurrency_challenge.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.cryptocurrency_challenge.databinding.FragmentDetailCurrencysBinding
import com.example.cryptocurrency_challenge.viewmodel.CryptocurrencyViewModel

@SuppressLint("SetTextI18n")
class DetailCurrencysFragment : Fragment() {

    private var _binding: FragmentDetailCurrencysBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CryptocurrencyViewModel by viewModels()
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
            viewModel.getInfoTicker(currency_name)
            viewModel.isLoading.observe(viewLifecycleOwner, Observer {
                loading.isVisible = it
            })
            viewModel.payLoadTicker?.observe(viewLifecycleOwner, Observer { it ->
                txtLastPriceAmount.text = "$${it?.last}"
                txtHighestPriceAmount.text = "$${it?.high}"
                txtLowestPriceAmount.text = "$${it?.low}"
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
