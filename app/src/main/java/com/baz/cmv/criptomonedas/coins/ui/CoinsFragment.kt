package com.baz.cmv.criptomonedas.coins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.baz.cmv.criptomonedas.coins.Coins
import com.baz.cmv.criptomonedas.coins.core.RetrofitService
import com.baz.cmv.criptomonedas.coins.data.remote.network.CoinsApiClient
import com.baz.cmv.criptomonedas.coins.data.remote.network.CoinsService
import com.baz.cmv.criptomonedas.coins.ui.viewmodel.CoinsViewModel
import com.baz.cmv.criptomonedas.databinding.FragmentCriptomonedaBinding
import com.baz.cmv.criptomonedas.databinding.FragmentCriptomonedaBinding.inflate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CoinsFragment : Fragment() {

    private lateinit var binding: FragmentCriptomonedaBinding
    lateinit var coinsViewModel: CoinsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, container, false)

        coinsViewModel = ViewModelProvider(this)[CoinsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CriptoListAdapter { coin ->
            findNavController().navigate(CoinsFragmentDirections.actionCoinsFragmentToBlankFragment())
        }

        coinsViewModel.monedas.observe(viewLifecycleOwner) { resultado ->
            binding.criptomonedasRecyclerview.adapter = adapter
            adapter.submitList(resultado)
        }
    }

}


/*    ItemClickSupport.addTo(recycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
        @Override
        public void onItemClicked(RecyclerView recyclerView, int position, View v){

            Fragment fragment = new NegocioFragment();
            Bundle args = new Bundle();
            args.putString("data", "This data has sent to FragmentTwo");
            fragment.setArguments(args);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_main, fragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(null);
            transaction.commit();
            Toast.makeText(getActivity().getApplicationContext(), "Diste click en el número: "+recycler.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();
        }
    });*/



/* fun obtenerMonedas(monedasObtenidas: (List<Coins>) -> Unit){
  //hacer llamada de retrofit
  monedasObtenidas(CoinGenerator.coins.subList(0,1))

      //cuando conteste, retornar con el llamado de monedasObtenidas
}*/
