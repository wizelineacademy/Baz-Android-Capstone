package wizeline.crypto.currency.ui.informationTrading

import wizeline.crypto.currency.domain.model.OrderbookModel
import wizeline.crypto.currency.domain.model.TradingInformationModel

data class TradingState(
    val information: TradingInformationModel = TradingInformationModel(),
    val orderBook: OrderbookModel= OrderbookModel(),
    val isLoading: Boolean = false
)