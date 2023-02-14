package com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.AvailableBooksViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.BookOrderViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.views.adapters.BookOrderAdapter

class BookOrderListFragment : Fragment() {

    private var columnCount = 1

    //private val viewModel: BooksScreenViewModel by viewModels()
    lateinit var availableBooksViewModel: AvailableBooksViewModel
    lateinit var bookOrderViewModel: BookOrderViewModel

    val bookOrderAdapter = BookOrderAdapter{ bookOrderSelected ->
        log("z0", "selected $bookOrderSelected")
        availableBooksViewModel.selectedBookOrder = bookOrderSelected
        bookOrderViewModel.selectedBookOrder = bookOrderSelected
        navigateToTicker()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_order_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = bookOrderAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        availableBooksViewModel = ViewModelProvider(requireActivity()).get(AvailableBooksViewModel::class.java)
        bookOrderViewModel = ViewModelProvider(requireActivity()).get(BookOrderViewModel::class.java)

        availableBooksViewModel.books.observe(viewLifecycleOwner) {
            log("z0", "Fragment ${it}" )
            bookOrderAdapter.updateBookOrders(it)
        }

        if (bookOrderViewModel.isInternetAvailable){
            availableBooksViewModel.getBooksWithUseCase()
        } else {
            log("z0", "TODO : Retrieve available data from db")
            //TODO retrieve from db
            availableBooksViewModel.getBooksWithUseCase()
        }
    }

    fun navigateToTicker(){
        val fragment = TickerFragment()
        val fm : FragmentManager = requireActivity().supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.cl_cointainer, fragment)
        ft.addToBackStack(TickerFragment.TAG)
        ft.commit()
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        const val TAG = "BookOrderListFragment"

        @JvmStatic
        fun newInstance(columnCount: Int = 1) =
            BookOrderListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}