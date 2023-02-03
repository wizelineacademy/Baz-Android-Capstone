package com.axiasoft.android.zerocoins.features.coins.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.features.coins.viewmodels.BooksScreenViewModel
import com.axiasoft.android.zerocoins.features.coins.views.adapters.BookOrderAdapter

class BookOrderListFragment : Fragment() {

    private var columnCount = 1

    private val viewModel: BooksScreenViewModel by viewModels()

    val bookOrderAdapter = BookOrderAdapter{ bookOrderSelected ->
        log("z0", "selected $bookOrderSelected")
        viewModel.selectedBookOrder = bookOrderSelected
        //TODO set data and navigate to next screen
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
        viewModel.books.observe(viewLifecycleOwner) {
            log("z0", "Fragment ${it}" )
            bookOrderAdapter.updateBookOrders(it)
        }
        viewModel.getBooksWithUseCase()
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