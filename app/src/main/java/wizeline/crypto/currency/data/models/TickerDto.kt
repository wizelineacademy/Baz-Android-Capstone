package wizeline.crypto.currency.data.models

import wizeline.crypto.currency.domain.model.TradingInformationModel

data class TickerDto(
    val payload: PayloadTickerDto,
    val success: Boolean
)

fun TickerDto.convertToTradingInformation(): TradingInformationModel {
    return TradingInformationModel(
        book = payload.book,
        volume = payload.volume,
        high = payload.high,
        last = payload.last,
        low = payload.low,
        wap = payload.wap,
        ask = payload.ask,
        bid = payload.bid,
        created_at = payload.created_at
    )
}