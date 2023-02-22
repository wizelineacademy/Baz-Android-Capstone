package com.javg.cryptocurrencies.view.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.facebook.shimmer.ShimmerFrameLayout
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.databinding.CryBookFragmentBinding
import com.javg.cryptocurrencies.view.book.recyclerview.CRYBookRecyclerView
import com.javg.cryptocurrencies.view.book.recyclerview.CRYChipsHeaderRecyclerView
import com.javg.cryptocurrencies.view.detail.CRYDetailBookFragment
import com.javg.cryptocurrencies.view.viewmodel.CRYHomeVM
import kotlin.math.abs

/**
 * @author Juan Vera Gomez
 * Date modified 10/02/2023
 *
 * Fragment in charge of manipulating and displaying the
 * information corresponding to the cryptocurrency cards
 *
 * @since 2.1
 */
class CRYBookFragment : Fragment(){

    private lateinit var binding: CryBookFragmentBinding
    private lateinit var adapterBook: CRYBookRecyclerView
    private lateinit var adapterChips: CRYChipsHeaderRecyclerView
    private val bookHomeVM by activityViewModels<CRYHomeVM>()
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    //Saves the Lambda event that is executed in the adapter item
    private val onClickItem: (String, String) -> Unit = { book, image ->
        nextFragment(CRYDetailBookFragment.newInstance(book,image))
    }

    companion object{
        val TAG = CRYBookFragment::class.java.canonicalName!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CryBookFragmentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadShimmer()
        loadAdapter()
        loadViewPagerChips()
        swipeRefresh()
        bookHomeVM.getBooks()
        bookHomeVM.updateTime.observe(viewLifecycleOwner, observerUpdateTime)
        bookHomeVM.chipsTitles.observe(viewLifecycleOwner, observerBooksChips)
        bookHomeVM.equalBooks.observe(viewLifecycleOwner, observerEqualsBooks)
    }

    /**
     * Performs an update when the user interacts with
     * the view in order to update the books in the database
     */
    private fun swipeRefresh(){
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            binding.rvBooks.visibility = View.GONE
            loadShimmer()
            bookHomeVM.getBooks(true)
        }
    }

    /**
     * It is responsible for linking the visual component
     * and starting the facebook loading effect
     */
    private fun loadShimmer(){
        binding.idShimmer.visibility = View.VISIBLE
        shimmerFrameLayout = binding.idShimmer
        shimmerFrameLayout.startShimmer()
    }

    /**
     * Takes care of the adapter instance and the visual
     * configuration of the recycle view
     */
    private fun loadAdapter(){
        adapterBook = CRYBookRecyclerView(requireContext(), onClickItem)
        binding.rvBooks.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter       = adapterBook
        }
    }

    /**
     * Start the general configuration of the viewPager of the view
     */
    private fun loadViewPagerChips(){
        adapterChips = CRYChipsHeaderRecyclerView(requireContext())
        binding.includeChips.vpChips.adapter = adapterChips
        setupViewPager()
        onPagerListenerChips()
    }

    /**
     * Configure the viewPager to transform the size of the card that is selected
     */
    private fun setupViewPager(){
        with(binding){
            includeChips.vpChips.clipToPadding = false
            includeChips.vpChips.clipChildren = false
            includeChips.vpChips.offscreenPageLimit = 3
            includeChips.vpChips.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer { page, position ->
                page.apply {
                    val r = 1 - abs(position)
                    scaleY = (0.20f + r + 0.15f)
                }
            }
            includeChips.vpChips.setPageTransformer(compositePageTransformer)
            //redireccionar a posicion especifica
            /*includeChips.vpChips.post {
                includeChips.vpChips.setCurrentItem(5,false)
            }*/
        }
    }

    /**
     * Contains the functionality to be able to detect when the user
     * has changed the page in the view
     */
    private fun onPagerListenerChips(){
        binding.includeChips.vpChips.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                println("onPageSelected -> $position")
                bookHomeVM.updateListDifferentBook(position)
            }
        })
    }

    /**
     * It is in charge of observing the list that contains the same
     * books of the page of selection of the view
     */
    private val observerEqualsBooks = Observer<List<CRYBook>>{ equalsBooks ->
        equalsBooks?.let {
            swipeRefreshLayout.isRefreshing = false

            if (equalsBooks.isEmpty())
                Toast.makeText(requireContext(),"Lista vacia",Toast.LENGTH_SHORT).show()
            else{
                shimmerFrameLayout.stopShimmer()
                binding.idShimmer.visibility = View.GONE
                binding.rvBooks.visibility   = View.VISIBLE
                adapterBook.submitList(equalsBooks)
            }
        }
    }

    /**
     * Look at the list that contains the unique books to
     * be able to show the selection options
     *
     */
    private val observerBooksChips = Observer<List<CRYBook>> {listBooks ->
        listBooks?.let {
            adapterChips.submitList(listBooks)
        }
    }

    /**
     * Observes the time change and reflects it in the view
     */
    private val observerUpdateTime = Observer<String>{
       it?.let { updateTime ->
           binding.updateDay.text = updateTime
       }
    }

    /**
     * It is in charge of configuring the passage to the next fragment of the application
     */
    private fun nextFragment(fragment: Fragment){
        val fm = requireActivity().supportFragmentManager.beginTransaction()
        fm.replace(R.id.root_layout, fragment)
        fm.addToBackStack(CRYDetailBookFragment.TAG)
        fm.commit()
    }

}