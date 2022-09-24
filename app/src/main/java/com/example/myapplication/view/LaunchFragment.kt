package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var list: List<CriptoCurrency>? = ArrayList()

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
            viewModel.getCriptoCurrency().observe(viewLifecycleOwner) {
                binding.progressCircular.visibility = View.INVISIBLE
                if (it != null) {
                    list = it
                    updateAdapter()
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWS()
    }

    private fun initWS() {
        viewModel.consultCriptoCurrency()
    }

    private fun updateAdapter() {
        criptoAdapter.submitList(list)
    }

    override fun onCellClickListener(data: CriptoCurrency) {
        val bundle = bundleOf("idBitso" to data.name)
        findNavController().navigate(R.id.action_launchFragment_to_bitsoDetailFragment, bundle)
    }

}