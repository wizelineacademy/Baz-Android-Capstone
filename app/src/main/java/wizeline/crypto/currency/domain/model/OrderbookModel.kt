package wizeline.crypto.currency.domain.model

data class OrderbookModel(
    val asks: List<AsksBidsModel> ? = null,
    val bids: List<AsksBidsModel> ? = null
)