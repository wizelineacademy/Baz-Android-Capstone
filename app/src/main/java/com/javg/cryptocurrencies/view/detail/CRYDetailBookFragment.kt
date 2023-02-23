package com.javg.cryptocurrencies.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.view.viewmodel.CRYDetailBookVM
import com.javg.cryptocurrencies.databinding.CryDetailBookFragmentBinding
import com.javg.cryptocurrencies.utils.separateStringCoins
import com.javg.cryptocurrencies.view.detail.recyclerview.CRYAskRecyclerView

/**
 * @author Juan Antonio Vera
 * Date modified 22/02/2023
 *
 * They contain the necessary functionalities to show the detailed
 * information of a specific book visually.
 *
 * @since 2.1
 */
class CRYDetailBookFragment : Fragment(){
    private lateinit var binding: CryDetailBookFragmentBinding
    private val detailBookVM by activityViewModels<CRYDetailBookVM>()
    private lateinit var adapterAskBids: CRYAskRecyclerView
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private val lottieAsset = "empty_data.json"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CryDetailBookFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    /**
     * the functionality is overwritten, in which you have
     * the functions for the view and the data that is
     * requested by instance is obtained
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = arguments?.getString("BOOK").orEmpty()
        val imageId = arguments?.getString("IMAGE_NAME").orEmpty()
        loadData(book, imageId)
        onClickListener()
    }

    /**
     * It is in charge of displaying the information of the book in addition
     * to displaying the image that is received, configuring the adapter
     * and having the observers for when the information changes.
     *
     * @param book is the name of the book to show its details
     * @param imageName is the name of the image that will be consulted online to display
     */
    private fun loadData(book: String,imageName: String){
        if (book.isNotEmpty())
            detailBookVM.getTicker(book)

        Glide.with(requireContext())
            .load(imageName)
            .placeholder(R.drawable.ic_default_book)
            .into(binding.bookImage)
        binding.headerTopBar.title.text = book.separateStringCoins().uppercase()
        configureAdapter()

        detailBookVM.tickerBook.observe(viewLifecycleOwner, tickerObserver)
        detailBookVM.listAskOrBids.observe(viewLifecycleOwner, listAskOrBids)
        detailBookVM.emptyData.observe(viewLifecycleOwner, emptyDataObserver)
        detailBookVM.updateTime.observe(viewLifecycleOwner, observerUpdateTime)
    }

    /**
     * Configure and instantiate the adapter to display ask and bids
     */
    private fun configureAdapter(){
        adapterAskBids = CRYAskRecyclerView(requireContext())
        binding.rvAskBids.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterAskBids
        }
    }

    /**
     * listens for the click events of the view when the user interacts
     */
    private fun onClickListener() = with(binding){
        headerTopBar.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
        txtAsk.setOnClickListener { changeAsk() }
        txtBids.setOnClickListener { changeBids() }
    }

    /**
     * Control the visual configuration when selecting ask,
     * changing the design and color of the text and background
     */
    private fun changeAsk(){
        binding.txtAsk.background = AppCompatResources.getDrawable(requireContext(), R.drawable.background_button_line)
        binding.txtBids.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.transparent))
        detailBookVM.tickerBook.value?.askList?.let { detailBookVM.sendListUpdate(it) }
    }

    /**
     * Control the visual configuration when selecting bids,
     * changing the design and color of the text and background
     */
    private fun changeBids(){
        binding.txtAsk.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.transparent))
        binding.txtBids.background = AppCompatResources.getDrawable(requireContext(), R.drawable.background_button_line)
        detailBookVM.tickerBook.value?.bidsList?.let { detailBookVM.sendListUpdate(it) }
    }

    private fun startAnimationNoData(){
        binding.clNotInformation.visibility = View.VISIBLE
        binding.lavNotInformation.imageAssetsFolder = "assets"
        binding.lavNotInformation.setAnimation(lottieAsset)
        binding.lavNotInformation.repeatCount = LottieDrawable.INFINITE
        binding.lavNotInformation.playAnimation()
    }

    private fun hideContentAll(){
        binding.bookImage.visibility      = View.GONE
        binding.txtLastPrice.visibility   = View.GONE
        binding.edLastPrice.visibility    = View.GONE
        binding.clContainerAll.visibility = View.GONE
    }

    private fun showContentAll(){
        binding.bookImage.visibility        = View.VISIBLE
        binding.txtLastPrice.visibility     = View.VISIBLE
        binding.edLastPrice.visibility      = View.VISIBLE
        binding.clContainerAll.visibility   = View.VISIBLE
        binding.clNotInformation.visibility = View.GONE
    }

    /**
     * Observe when the data of the prices of the specific book changes
     */
    private val tickerObserver = Observer<CRYDetailBook>{
        it?.let {
            with(binding){
                edLastPrice.text                = String.format(requireContext().resources.getString(R.string.cry_format_amount), it.last)
                inCardPrice.edHighestPrice.text = String.format(requireContext().resources.getString(R.string.cry_format_amount), it.high)
                inCardPrice.edLowMorePrice.text = String.format(requireContext().resources.getString(R.string.cry_format_amount), it.low)
                showContentAll()
            }
        }
    }

    /**
     * watches when the ask or bid list changes informing
     * the adapter of the change, as well as the
     * corresponding visual changes
     */
    private val listAskOrBids = Observer<List<CRYAskOrBids>> {
        it?.let {
            adapterAskBids.submitList(it)
        }
    }

    private val emptyDataObserver = Observer<Boolean>{
        it?.let {
            if (it) {
                startAnimationNoData()
                hideContentAll()
            }
        }
    }

    /**
     * Observes the time change and reflects it in the view
     */
    private val observerUpdateTime = Observer<String>{
        it?.let { updateTime ->
            binding.headerTopBar.txtLastUpdate.text = updateTime
        }
    }
}