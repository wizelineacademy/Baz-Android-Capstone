package wizeline.crypto.currency.ui.homeCurrency

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import wizeline.crypto.currency.databinding.ActivityMainBinding
import wizeline.crypto.currency.ui.homeCurrency.adapters.AvailableBooksAdapter
import wizeline.crypto.currency.ui.homeCurrency.viewModel.HomeViewModel
import wizeline.crypto.currency.ui.informationTrading.TradingInformationActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel by viewModels<HomeViewModel>()

    private var avilableAdapter = AvailableBooksAdapter { data ->

        var intent = Intent (applicationContext, TradingInformationActivity::class.java)
        intent.putExtra("book",data.book)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        binding.apply {
            rcvCurrencies.apply {
                setHasFixedSize(true)
                adapter= avilableAdapter
                layoutManager= LinearLayoutManager(applicationContext)
            }
        }

        homeViewModel.getAvailableBook()

        homeViewModel.state.observe(this) { uiState ->
            avilableAdapter.submitList(uiState.book)
        }

    }


}