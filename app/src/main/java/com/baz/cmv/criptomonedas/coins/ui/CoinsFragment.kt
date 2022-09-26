package com.baz.cmv.criptomonedas.coins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.baz.cmv.criptomonedas.coins.CoinGenerator
import com.baz.cmv.criptomonedas.coins.Coins
import com.baz.cmv.criptomonedas.coins.remote.RetrofitService
import com.baz.cmv.criptomonedas.coins.service.CoinsService
import com.baz.cmv.criptomonedas.databinding.FragmentCriptomonedaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET


class CoinsFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentCriptomonedaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCriptomonedaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit = RetrofitService.getRetrofitInstance()
        val coinsService = retrofit.create(CoinsService::class.java)
        val adapter = CriptoListAdapter()
        val recycler = binding.criptomonedasRecyclerview
        recycler.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO){
                val resultado = coinsService.obtenerMonedas()
                withContext(Dispatchers.Main.immediate){
                    val listCoin = resultado.monedas.map { networkMoneda ->
                        Coins(
                            criptomoneda = networkMoneda.book,
                            precio = networkMoneda.maximumPrice.toDouble()
                        )
                    }
                    binding.criptomonedasRecyclerview.adapter = adapter
                    adapter.submitList(listCoin)
                }

            }

        }
    }
}


/*  obtenerMonedas(monedasObtenidas = {
      binding.criptomonedasRecyclerview.adapter = CoinsAdapter(it)
  })/*
}

override fun onDestroyView() {
  super.onDestroyView()
  _binding = null
}
}


/* fun obtenerMonedas(monedasObtenidas: (List<Coins>) -> Unit){
  //hacer llamada de retrofit
  monedasObtenidas(CoinGenerator.coins.subList(0,1))

      //cuando conteste, retornar con el llamado de monedasObtenidas
}*/


/*
viewModelScope.launch(Dispatchers.IO) {
_repo.update{ service.getRepositories() }
}
*/