package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.BitsoDetailFragmentBinding
import com.example.myapplication.util.formatAsCurrency
import com.example.myapplication.view.adapter.AskBidAdapter
import com.example.myapplication.view.adapter.CritpAdapter
import com.example.myapplication.viewModel.BitsoViewModel

/**
 * Created by: Juan Antonio Amado
 * date: 21,septiembre,2022
 */
class BitsoDetailFragment : Fragment() {

    private lateinit var binding: BitsoDetailFragmentBinding
    private var idBitso = ""
    private lateinit var viewModel: BitsoViewModel
    private val adapter = AskBidAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = BitsoDetailFragmentBinding.inflate(inflater, container, false)
            idBitso = arguments?.getString("idBitso").toString()
            viewModel = ViewModelProvider(this)[BitsoViewModel::class.java]
           /* binding.recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = criptoAdapter*/
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

        viewModel.getSelectCriptoCurrency().observe(viewLifecycleOwner) {
            binding.progressCircular.visibility = View.INVISIBLE
            binding.txtHighPrice.text = PRICE_HIGH + it?.payload?.high?.toDouble()?.formatAsCurrency()
            binding.txtLowPrice.text = PRICE_LOW + it?.payload?.low?.toDouble()?.formatAsCurrency()
            binding.txtLastPrice.text = PRICE_LAST + it?.payload?.last?.toDouble()?.formatAsCurrency()

            when (it?.payload?.book) {
                "btc_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_bitcoin)
                    binding.textoName.setText(R.string.btc_mxn)
                }
                "eth_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_eth)
                    binding.textoName.setText(R.string.eth_mxn)
                }

                "xrp_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_xrp)
                    binding.textoName.setText(R.string.xrp_mxn)
                }
                "ltc_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_ltc)
                    binding.textoName.setText(R.string.ltc_mxn)
                }
                "bch_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_bch)
                    binding.textoName.setText(R.string.bch_mxn)
                }
                "tusd_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_tusd)
                    binding.textoName.setText(R.string.tusd_mxn)
                }
                "mana_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_mana)
                    binding.textoName.setText(R.string.mana_mxn)
                }
                "bat_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_bat)
                    binding.textoName.setText(R.string.bat_mxn)
                }
                "dai_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_dai)
                    binding.textoName.setText(R.string.dai_mxn)
                }
                "usd_mxn" -> {
                    binding.imagen.setBackgroundResource(R.drawable.ic_usd)
                    binding.textoName.setText(R.string.usd_mxn)
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