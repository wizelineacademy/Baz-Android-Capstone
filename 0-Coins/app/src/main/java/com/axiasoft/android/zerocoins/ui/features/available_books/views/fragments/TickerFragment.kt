package com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.axiasoft.android.zerocoins.databinding.FragmentTickerBinding
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.AvailableBooksViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.BookOrderViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.TickerViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.ListOrderBookScreenState
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.TickerScreenState

class TickerFragment : Fragment() {

    //private val viewModel: BooksScreenViewModel by viewModels()
    lateinit var bookOrderViewModel: BookOrderViewModel
    lateinit var tickerViewModel: TickerViewModel

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
        tickerViewModel  = ViewModelProvider(requireActivity()).get(TickerViewModel::class.java)

        bookOrderViewModel  = ViewModelProvider(requireActivity()).get(BookOrderViewModel::class.java)

        tickerViewModel.tickerState.observe(viewLifecycleOwner){
            when(it){
                is TickerScreenState.TickerSuccess ->{
                    binding.tvTicker.text = it.ticker.toString()
                }
                else -> {}
            }
        }

        tickerViewModel.listOrderBookScreenState.observe(viewLifecycleOwner){
            when(it){
                is ListOrderBookScreenState.Success ->{
                    binding.tvProvisionalListOrderDisplay.text = "ASKs: -> \n ${it.asks}"
                    binding.tvProvisionalListOrderBidsDisplay.text = "BIDs: -> \n ${it.bids}"
                }
                else -> {}
            }
        }

        tickerViewModel.selectedBookOrder = bookOrderViewModel.selectedBookOrder

        tickerViewModel.getTickerWithUseCase()
        tickerViewModel.getListOrderBook()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            TickerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}