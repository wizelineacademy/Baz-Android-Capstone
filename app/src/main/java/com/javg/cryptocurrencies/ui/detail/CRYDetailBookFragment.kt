package com.javg.cryptocurrencies.ui.detail

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.data.viewmodel.CRYDetailBookVM
import com.javg.cryptocurrencies.databinding.CryDetailBookFragmentBinding
import com.javg.cryptocurrencies.ui.detail.recyclerview.CRYAskRecyclerView

class CRYDetailBookFragment : Fragment(){
    private lateinit var binding: CryDetailBookFragmentBinding
    private val detailBookVM by activityViewModels<CRYDetailBookVM>()
    private lateinit var adapterAskBids: CRYAskRecyclerView
    private var listAux = mutableListOf<CRYAskOrBids>()
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

    companion object{
        val TAG = CRYDetailBookFragment::class.java.canonicalName!!

        @JvmStatic
        fun newInstance(book: String,@DrawableRes imageId: Int) : CRYDetailBookFragment {
            val fragment = CRYDetailBookFragment()
            val bundle = Bundle()
            bundle.putString("BOOK",book)
            bundle.putInt("IMAGE_ID",imageId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CryDetailBookFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = arguments?.getString("BOOK").orEmpty()
        val imageId = arguments?.getInt("IMAGE_ID") ?: R.drawable.ic_dai
        loadData(book, imageId)
        onClickListener()
    }

    private fun loadData(book: String,@DrawableRes imageId: Int){
        shimmerFrameLayout = binding.idShimmer
        binding.bookImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), imageId))
        binding.txtBook.text = book
        detailBookVM.tickerBook.observe(viewLifecycleOwner, tickerObserver)
        detailBookVM.listAskOrBids.observe(viewLifecycleOwner, listAskOrBids)
        adapterAskBids = CRYAskRecyclerView(listAux, requireContext())

        binding.rvAskBids.setHasFixedSize(false)
        val mLayoutManager = LinearLayoutManager(context)
        binding.rvAskBids.layoutManager = mLayoutManager

        if (book.isNotEmpty())
            detailBookVM.getTicker(book)
    }

    private fun onClickListener() = with(binding){
        ivArrowBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        txtAsk.setOnClickListener {
            changeAsk()
        }

        txtBids.setOnClickListener {
            changeBids()
        }
    }

    private fun changeAsk(){
        binding.txtAsk.background = AppCompatResources.getDrawable(requireContext(), R.drawable.background_button)
        binding.txtBids.background = AppCompatResources.getDrawable(requireContext(), R.drawable.background_disable)
        binding.txtAsk.setTextColor(AppCompatResources.getColorStateList(requireContext(), R.color.white))
        binding.txtBids.setTextColor(AppCompatResources.getColorStateList(requireContext(), R.color.blue))
        detailBookVM.setUpdateList(true)
    }

    private fun changeBids(){
        binding.txtAsk.background = AppCompatResources.getDrawable(requireContext(), R.drawable.background_disable)
        binding.txtBids.background = AppCompatResources.getDrawable(requireContext(), R.drawable.background_button)
        binding.txtBids.setTextColor(AppCompatResources.getColorStateList(requireContext(), R.color.white))
        binding.txtAsk.setTextColor(AppCompatResources.getColorStateList(requireContext(), R.color.blue))
        detailBookVM.setUpdateList(false)
    }

    private val tickerObserver = Observer<CRYDetailBook>{
        it?.let {
            with(binding){
                rvAskBids.adapter = adapterAskBids
                txtLastPrice.text    = String.format(requireContext().resources.getString(R.string.cry_last_price), it.last)
                txtHighestPrice.text = String.format(requireContext().resources.getString(R.string.cry_highest_price), it.high)
                txtLowMoreLow.text   = String.format(requireContext().resources.getString(R.string.cry_low_more_low), it.low)
            }
        }
    }

    private val listAskOrBids = Observer<MutableList<CRYAskOrBids>> {
        it?.let {
            binding.idShimmer.visibility = View.GONE
            binding.rvAskBids.visibility = View.VISIBLE
            binding.rlButtons.visibility = View.VISIBLE
            binding.txtLastPrice.visibility = View.VISIBLE
            binding.txtHighestPrice.visibility = View.VISIBLE
            binding.txtLowMoreLow.visibility = View.VISIBLE
            binding.bookImage.visibility = View.VISIBLE
            binding.clContainerHeader.visibility = View.VISIBLE
            adapterAskBids.update(it)
        }
    }
}