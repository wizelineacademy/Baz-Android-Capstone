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
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.view.viewmodel.CRYDetailBookVM
import com.javg.cryptocurrencies.databinding.CryDetailBookFragmentBinding
import com.javg.cryptocurrencies.ext.separateStringCoins
import com.javg.cryptocurrencies.view.detail.recyclerview.CRYAskRecyclerView

/**
 * @author Juan Antonio Vera
 *
 * They contain the necessary functionalities to show the detailed
 * information of a specific book visually.
 *
 * @since 2.0
 */
class CRYDetailBookFragment : Fragment(){
    private lateinit var binding: CryDetailBookFragmentBinding
    private val detailBookVM by activityViewModels<CRYDetailBookVM>()
    private lateinit var adapterAskBids: CRYAskRecyclerView
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private val lottieAsset = "empty_data.json"
    private var flag: Boolean = false

    companion object{
        val TAG = CRYDetailBookFragment::class.java.canonicalName!!

        /**
         * is responsible for generating an instance of the
         * fragment receiving two parameters and return
         *
         * @param book is the name of the book to show its details
         * @param imageName is the name of the image that will be consulted online to display
         */
        @JvmStatic
        fun newInstance(book: String,imageName: String) : CRYDetailBookFragment {
            val fragment = CRYDetailBookFragment()
            val bundle = Bundle()
            bundle.putString("BOOK",book)
            bundle.putString("IMAGE_NAME",imageName)
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

    /**
     * the functionality is overwritten, in which you have
     * the functions for the view and the data that is
     * requested by instance is obtained
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmerLoad()
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
     * Instantiate the component and initialize the facebook loading effect
     */
    private fun shimmerLoad(){
        shimmerFrameLayout = binding.idShimmer
        shimmerFrameLayout.startShimmer()
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
            requireActivity().supportFragmentManager.popBackStack()
        }
        headerTopBar.imageClose.setOnClickListener {
            requireActivity().finish()
        }
        txtAsk.setOnClickListener { changeAsk() }
        txtBids.setOnClickListener { changeBids() }
        ivHideShowDetail.setOnClickListener {
            if (flag){
                ivHideShowDetail.setImageResource(R.drawable.ic_arrow_up)
                showContentDetail()
                flag = false
            }else{
                ivHideShowDetail.setImageResource(R.drawable.ic_arrow_down)
                hideContentDetail()
                flag = true
            }
        }
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

    private fun hideContentDetail(){
        binding.txtOne.visibility           = View.GONE
        binding.txtTwo.visibility           = View.GONE
        binding.txtThree.visibility         = View.GONE
        binding.txtLastPrice.visibility     = View.GONE
        binding.txtHighestPrice.visibility  = View.GONE
        binding.txtLowMoreLow.visibility     = View.GONE
    }

    private fun showContentDetail(){
        binding.txtOne.visibility           = View.VISIBLE
        binding.txtTwo.visibility           = View.VISIBLE
        binding.txtThree.visibility         = View.VISIBLE
        binding.txtLastPrice.visibility     = View.VISIBLE
        binding.txtHighestPrice.visibility  = View.VISIBLE
        binding.txtLowMoreLow.visibility     = View.VISIBLE
    }

    private fun startAnimationNoData(){
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.visibility = View.GONE
        binding.clNoInformation.visibility = View.VISIBLE
        binding.laNoInformation.imageAssetsFolder = "assets"
        binding.laNoInformation.setAnimation(lottieAsset)
        binding.laNoInformation.repeatCount = LottieDrawable.INFINITE
        binding.laNoInformation.playAnimation()
    }

    private fun hideContentAll(){
        binding.clContentInformation.visibility = View.GONE
        binding.rlButtons.visibility = View.GONE
        binding.rvAskBids.visibility = View.GONE
    }

    /**
     * Observe when the data of the prices of the specific book changes
     */
    private val tickerObserver = Observer<CRYDetailBook>{
        it?.let {
            with(binding){
                txtLastPrice.text    = String.format(requireContext().resources.getString(R.string.cry_format_amount), it.last)
                txtHighestPrice.text = String.format(requireContext().resources.getString(R.string.cry_format_amount), it.high)
                txtLowMoreLow.text   = String.format(requireContext().resources.getString(R.string.cry_format_amount), it.low)
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
            with(binding){
                idShimmer.stopShimmer()
                idShimmer.visibility = View.GONE
                binding.clNoInformation.visibility = View.GONE
                binding.laNoInformation.cancelAnimation()
                rvAskBids.visibility = View.VISIBLE
                binding.clContentInformation.visibility = View.VISIBLE
                binding.rlButtons.visibility = View.VISIBLE
                binding.rvAskBids.visibility = View.VISIBLE

            }

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