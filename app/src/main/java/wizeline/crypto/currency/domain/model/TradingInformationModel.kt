package wizeline.crypto.currency.domain.model


data class TradingInformationModel(
    val book  :    String ?=null,
    val volume:    String ?=null,
    val high  :    String ?=null,
    val last  :    String ?=null,
    val low   :    String ?=null,
    val wap   :    String ?=null,
    val ask   :    String ?=null,
    val bid   :    String ?=null,
    val created_at:String ?=null
)