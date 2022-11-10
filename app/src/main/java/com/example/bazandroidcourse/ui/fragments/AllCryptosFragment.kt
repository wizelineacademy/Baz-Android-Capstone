package com.example.bazandroidcourse.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.databinding.FragmentAllCryptosBinding
import com.example.bazandroidcourse.ui.fragments.adapters.BooksAdapter
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

class AllCryptosFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private val viewModel: BooksViewModel = BooksViewModel.createInstance()
    private lateinit var binding: FragmentAllCryptosBinding
    private var currentCurrency = "mxn"
    private val adapter = BooksAdapter() {
        navigateTo(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCryptosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllBooks(currentCurrency)
        initVerticalList()
        initSpinner(this)

        addObservers()
    }

    fun initGrdList() {
        binding.recyclerBooks.layoutManager = GridLayoutManager(
            requireContext(),
            3
        )
        binding.recyclerBooks.adapter = adapter
    }

    fun initVerticalList() {
        binding.recyclerBooks.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerBooks.adapter = adapter
    }

    fun initSpinner(allCryptosFragment: AllCryptosFragment) = with(binding) {

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.names,
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerCurrencies.adapter = adapter
        }
        binding.spinnerCurrencies.onItemSelectedListener = allCryptosFragment
    }

    fun addObservers() {
        viewModel.allBooks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    fun navigateTo(item: BookModel) {
        val action = AllCryptosFragmentDirections.actionAllCryptosToDetail(item.book)
        findNavController().navigate(action)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.getAllBooksByCurrency(
            viewModel.names.get(position)
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


}