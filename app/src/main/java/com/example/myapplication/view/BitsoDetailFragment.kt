package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.BitsoDetailFragmentBinding
import com.example.myapplication.model.Ask
import com.example.myapplication.model.Bid
import com.example.myapplication.model.BitsoCustom
import com.example.myapplication.util.formatAsCurrency
import com.example.myapplication.view.adapter.AskBidAdapter2
import com.example.myapplication.viewModel.BitsoViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by: Juan Antonio Amado
 * date: 21,septiembre,2022
 */
@AndroidEntryPoint
class BitsoDetailFragment : Fragment() {

    private lateinit var binding: BitsoDetailFragmentBinding
    private var idBitso = ""
    private val viewModel: BitsoViewModel by viewModels()
    private val adapter = AskBidAdapter2()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = BitsoDetailFragmentBinding.inflate(inflater, container, false)
            idBitso = arguments?.getString(LaunchFragment.ID_BITSO).toString()
            binding.recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWS()
    }

    @SuppressLint("SetTextI18n")
    private fun initWS() {
        binding.progressCircular.visibility = View.VISIBLE
        viewModel.selectCriptoCurrency(id = idBitso)
        viewModel.getAskAndBidsCurrency(id = idBitso)

        viewModel.getAskBidCriptoCurrency().observe(viewLifecycleOwner) {
            it?.let {
                adapter.addData(it.payload.asks as ArrayList<Ask>)
                adapter.addDataBids(it.payload.bids as ArrayList<Bid>)
            }
        }

        binding.botonFinalizar.setOnClickListener {
            findNavController().popBackStack()
        }
        val list: ArrayList<BitsoCustom> = ArrayList()
        list.add(BitsoCustom(idBook = "btc_mxn", icon = R.drawable.ic_bitcoin, name = R.string.btc_mxn))
        list.add(BitsoCustom(idBook = "eth_mxn", icon = R.drawable.ic_eth, name = R.string.eth_mxn))
        list.add(BitsoCustom(idBook = "xrp_mxn", icon = R.drawable.ic_xrp, name = R.string.xrp_mxn))
        list.add(BitsoCustom(idBook = "ltc_mxn", icon = R.drawable.ic_ltc, name = R.string.ltc_mxn))
        list.add(BitsoCustom(idBook = "bch_mxn", icon = R.drawable.ic_bch, name = R.string.bch_mxn))
        list.add(BitsoCustom(idBook = "tusd_mxn", icon = R.drawable.ic_tusd, name = R.string.tusd_mxn))
        list.add(BitsoCustom(idBook = "mana_mxn", icon = R.drawable.ic_mana, name = R.string.mana_mxn))
        list.add(BitsoCustom(idBook = "bat_mxn", icon = R.drawable.ic_bat, name = R.string.bat_mxn))
        list.add(BitsoCustom(idBook = "dai_mxn", icon = R.drawable.ic_bitcoin, name = R.string.dai_mxn))
        list.add(BitsoCustom(idBook = "usd_mxn", icon = R.drawable.ic_usd, name = R.string.usd_mxn))

        viewModel.getSelectCriptoCurrency().observe(viewLifecycleOwner) {
            it?.let {
                binding.apply {
                    progressCircular.visibility = View.INVISIBLE
                    txtHighPrice.text = PRICE_HIGH + it.payload.high.toDouble().formatAsCurrency()
                    txtLowPrice.text = PRICE_LOW + it.payload.low.toDouble().formatAsCurrency()
                    txtLastPrice.text = PRICE_LAST + it.payload.last.toDouble().formatAsCurrency()

                    list.forEach { bitsoCustom ->
                        if (bitsoCustom.idBook == it.payload.book) {
                            imagen.setBackgroundResource(bitsoCustom.icon)
                            textoName.setText(bitsoCustom.name)

                        } else {
                            textoName.text = it.payload.book
                            imagen.setBackgroundResource(R.drawable.ic_default_coin)
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val PRICE_HIGH = "Precio Alto: "
        const val PRICE_LOW = "Precio Bajo: "
        const val PRICE_LAST = "Precio Último: "
    }
}