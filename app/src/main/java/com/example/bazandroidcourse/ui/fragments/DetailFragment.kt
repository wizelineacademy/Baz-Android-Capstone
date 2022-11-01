package com.example.bazandroidcourse.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bazandroidcourse.databinding.FragmentDetailBinding
import com.example.bazandroidcourse.ui.fragments.adapters.BookOrdersAdapter
import com.example.bazandroidcourse.ui.utils.createURLImage
import com.example.bazandroidcourse.ui.utils.cryptoName
import com.example.bazandroidcourse.ui.utils.getTicker
import com.example.bazandroidcourse.ui.utils.getCurrency
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var bookId:String
    private lateinit var viewModel:BooksViewModel
    val asksAdapter = BookOrdersAdapter(){}
    val bidsAdapter = BookOrdersAdapter(){}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = BooksViewModel(requireContext())
        initLists()
        loadObservers()
        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
            bookId = args.bookId
            viewModel.getBookDetail(bookId)
            viewModel.getBookOrders(bookId)
            binding.apply {
                try{
                    Picasso.get()
                        .load(createURLImage(bookId))
                        .into(ivIcon)
                }catch (e:Exception){
                    e.printStackTrace()
                }
                tvCryptoName.text = bookId.cryptoName()
                tvCryptoTicker.text = getTicker(bookId)
                val unit = getCurrency(bookId)
                tvUpperUnit.text = unit
                tvLowerUnit.text = unit
                tvPriceUnit.text = unit
            }
        }
    }

    fun initLists() {
        binding.apply {
            recyclerAsks.layoutManager  = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            recyclerBinds.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            recyclerBinds.adapter   = bidsAdapter
            recyclerAsks.adapter    = asksAdapter
        }
    }

    fun loadObservers() {
        viewModel.currentBook.observe(viewLifecycleOwner){ book ->
            binding.apply {
                tvLowerPrice.text = book.low
                tvLastPrice.text  = book.last
                tvUperPrice.text  = book.high
                tvVolume.text = book.volume
            }
        }

        viewModel.currentBookOrders.observe(viewLifecycleOwner){bookOrder ->
            binding.apply {
                bidsAdapter.submitList(bookOrder.bids)
                asksAdapter.submitList(bookOrder.asks)
            }
        }
    }


}