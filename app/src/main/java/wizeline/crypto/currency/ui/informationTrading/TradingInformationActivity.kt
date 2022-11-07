package wizeline.crypto.currency.ui.informationTrading

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import wizeline.crypto.currency.databinding.ActivityTradingInformationBinding
import wizeline.crypto.currency.ui.homeCurrency.adapters.AvailableBooksAdapter
import wizeline.crypto.currency.ui.informationTrading.adapters.OrderBookAdapter
import wizeline.crypto.currency.ui.informationTrading.viewModel.TradingInformationViewModel
import wizeline.crypto.currency.utils.getDrawable

@AndroidEntryPoint
class TradingInformationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityTradingInformationBinding
    private val tradingViewModel by viewModels<TradingInformationViewModel>()

    var book:String=""

    private var asksAdapter = OrderBookAdapter()
    private var bidsAdapter = OrderBookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        book= intent.getStringExtra("book") ?: ""

        binding = ActivityTradingInformationBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)

        tradingViewModel.state.observe(this) { uiState ->
           Log.i("Datainfo", uiState.information.toString())


            binding.apply {
                txtVolume.text=uiState.information.volume
                txtHighn.text=uiState.information.high
                txtLast.text=uiState.information.last
                txtLow.text=uiState.information.low
                txtVwap.text=uiState.information.wap
                txtAsk.text=uiState.information.ask
                txtBid.text=uiState.information.bid
                txtCreated.text=uiState.information.created_at

                var currencyPrincipal=book.split("_")
                imgcurrency.setImageDrawable( getDrawable(
                        applicationContext, "ic_${currencyPrincipal[0]}")
                )
                imgcurrento.setImageDrawable(getDrawable(
                        applicationContext, "ic_${currencyPrincipal[1]}")
                )



                rcvAsks.apply {
                    setHasFixedSize(true)
                    adapter= asksAdapter
                    layoutManager= LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL,false)
                }

                rcvBids.apply {
                    setHasFixedSize(true)
                    adapter= bidsAdapter
                    layoutManager= LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL,false)
                }



            }

            asksAdapter.submitList(uiState.orderBook.asks)
            bidsAdapter.submitList(uiState.orderBook.bids)

        }


        tradingViewModel.getTradingInformation(book)
        tradingViewModel.getOrderBook(book)

    }

}