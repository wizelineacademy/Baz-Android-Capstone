package wizeline.crypto.currency.data.models


data class PayLoadOrderBook(
    val asks: List<asksBidsDto>,
    val bids: List<asksBidsDto>,
    val updated_at: String,
    val sequence:String
)