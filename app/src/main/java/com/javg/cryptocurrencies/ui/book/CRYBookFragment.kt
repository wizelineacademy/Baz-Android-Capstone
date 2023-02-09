package com.javg.cryptocurrencies.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.viewmodel.CRYHomeVM
import com.javg.cryptocurrencies.databinding.CryBookFragmentBinding
import com.javg.cryptocurrencies.ui.book.recyclerview.CRYBookRecyclerView
import com.javg.cryptocurrencies.ui.detail.CRYDetailBookFragment

/**
 * @author Juan Vera Gomez
 * Date modified 08/02/2023
 *
 * Fragment in charge of manipulating and displaying the
 * information corresponding to the cryptocurrency cards
 *
 * @since 2.0
 */
class CRYBookFragment : Fragment(){

    private lateinit var binding: CryBookFragmentBinding
    private lateinit var adapterBook: CRYBookRecyclerView
    private val bookHomeVM by activityViewModels<CRYHomeVM>()
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

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
        bookHomeVM.getBooks()
        bookHomeVM.listBook.observe(viewLifecycleOwner,observerBook)
    }

    /**
     * It is responsible for linking the visual component
     * and starting the facebook loading effect
     */
    private fun loadShimmer(){
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
     * In charge of observing when the list of cryptocurrencies
     * changes to show the or show a message that it has no data
     */
    private val observerBook = Observer<List<CRYBook>>{
        it.let { listBooks ->
            if (listBooks.isEmpty())
                Toast.makeText(requireContext(),"Lista vacia",Toast.LENGTH_SHORT).show()
            else{
                shimmerFrameLayout.stopShimmer()
                binding.idShimmer.visibility = View.GONE
                binding.rvBooks.visibility   = View.VISIBLE
                adapterBook.submitList(listBooks)
            }
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