package com.axiasoft.android.zerocoins.features.coins.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.databinding.FragmentTickerBinding
import com.axiasoft.android.zerocoins.features.coins.viewmodels.BooksScreenViewModel
import com.axiasoft.android.zerocoins.features.coins.views.ui_states.ListOrderBookScreenState
import com.axiasoft.android.zerocoins.features.coins.views.ui_states.TickerScreenState

class TickerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    //private val viewModel: BooksScreenViewModel by viewModels()
    lateinit var viewModel: BooksScreenViewModel

    private var _binding: FragmentTickerBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(BooksScreenViewModel::class.java)

        viewModel.tickerState.observe(viewLifecycleOwner){
            when(it){
                is TickerScreenState.TickerSuccess ->{
                    binding.tvTicker.text = it.ticker.toString()
                }
                else -> {}
            }
        }

        viewModel.listOrderBookScreenState.observe(viewLifecycleOwner){
            when(it){
                is ListOrderBookScreenState.Success ->{
                    binding.tvProvisionalListOrderDisplay.text = "ASKs: -> \n ${it.asks}"
                    binding.tvProvisionalListOrderBidsDisplay.text = "BIDs: -> \n ${it.bids}"
                }
                else -> {}
            }
        }

        viewModel.getTickerWithUseCase()
        viewModel.getListOrderBook()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TickerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TickerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}