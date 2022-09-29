package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLaunchBinding
import com.example.myapplication.model.CriptoCurrency
import com.example.myapplication.view.adapter.CritpAdapter
import com.example.myapplication.view.interfaces.OnclickListenerItem
import com.example.myapplication.viewModel.BitsoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchFragment : Fragment(), OnclickListenerItem {

    private val viewModel: BitsoViewModel by viewModels()
    lateinit var binding: FragmentLaunchBinding
    private val criptoAdapter = CritpAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = FragmentLaunchBinding.inflate(inflater, container, false)
            binding.recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = criptoAdapter
            binding.progressCircular.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWS()
    }
    override fun onResume() {
        super.onResume()
        initData()
    }
    private fun initData() {
        viewModel.consultAllcriptoCurrency()
    }

    private fun initWS() {

        viewModel.consultAllcriptoCurrency()
        binding.btnFilter.setOnClickListener {
            viewModel.consultFilterCriptoCurrency()
        }

        viewModel.getCriptoCurrency().observe(viewLifecycleOwner) {
            binding.progressCircular.visibility = View.INVISIBLE
            it?.let {
                updateAdapter(it)
            }
            viewModel.getCriptoCurrency().observe(viewLifecycleOwner) {
                binding.progressCircular.visibility = View.INVISIBLE
                it?.let {
                    updateAdapter(it)
                }?: Toast.makeText(requireContext(),"No tienes internet", Toast.LENGTH_SHORT).show()

            }

    }

    }

    private fun updateAdapter(list: List<CriptoCurrency>) {
        criptoAdapter.submitList(list)
    }

    override fun onCellClickListener(data: CriptoCurrency) {
        val bundle = bundleOf(ID_BITSO to data.name)
        findNavController().navigate(R.id.action_launchFragment_to_bitsoDetailFragment, bundle)
    }

    companion object {
        const val ID_BITSO = "idBitso"
    }

}