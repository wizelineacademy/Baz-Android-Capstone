package com.baz.cmv.criptomonedas.coins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.baz.cmv.criptomonedas.coins.ui.viewmodel.CoinsViewModel
import com.baz.cmv.criptomonedas.coins.ui.viewmodel.DetailViewModel
import com.baz.cmv.criptomonedas.databinding.FragmentDetallecirptomonedaBinding
import com.baz.cmv.criptomonedas.databinding.FragmentDetallecriptoBinding

class DetailsFragment : Fragment(){
    private lateinit var binding: FragmentDetallecriptoBinding
    lateinit var coinsViewModel: CoinsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetallecriptoBinding.inflate(inflater, container, false)

        coinsViewModel = ViewModelProvider(this)[CoinsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinsViewModel.detalle.observe(viewLifecycleOwner) {
            binding.textCriptomoneda.text = it.book
            binding.textMasAlto.text = it.maximumPrice
            binding.textMasBajo.text = it.minimumPrice
        }

    }

}