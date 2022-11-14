package com.example.bazandroidcourse.data.utils.mappers

import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderTypes
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResponsePayload
import com.example.bazandroidcourse.data.datasource.remote.api.response.DetailResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderPayloadResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderResume
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrderResumeModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.ui.utils.cryptoName

/***
 *  API response transformations
 */
fun DetailResponse.toDomain(): BookDetailModel {
    return BookDetailModel(
        book = book.orEmpty(),
        volume = volume.orEmpty(),
        high = high.orEmpty(),
        last = last.orEmpty(),
        low = low.orEmpty()
    )
}

fun BookResponsePayload.toDomain(): BookModel {
    return BookModel(
        book = book.orEmpty(),
        name = book?.cryptoName().orEmpty()
    )
}

fun List<BookResponsePayload>.toDomain(): List<BookModel> {
    return map {
        it.toDomain()
    }
}

fun OrderPayloadResponse.toDomain(): BookOrdersModel {
    return BookOrdersModel(
        asks = bids?.toDomainList() ?: emptyList(),
        bids = bids?.toDomainList() ?: emptyList()
    )
}

fun List<OrderResume>.toDomainList(): List<BookOrderResumeModel> {
    return map {
        it.toDomain()
    }
}

fun OrderResume.toDomain(): BookOrderResumeModel {
    return BookOrderResumeModel(
        book = book.orEmpty(),
        amount = amount.orEmpty(),
        price = price.orEmpty()
    )
}

fun List<DetailResponse>.toBookDetailList(): List<BookDetailEntity> {
    return map {
        it.toEntity()
    }
}

fun DetailResponse.toEntity(): BookDetailEntity {
    return BookDetailEntity(
        book = book.orEmpty(),
        volume = volume.orEmpty(),
        high = high.orEmpty(),
        low = low.orEmpty(),
        last = last.orEmpty()
    )
}

/***
 * Entity transformations
 */
fun List<BookOrderEntity>.toOrderDomain(): BookOrdersModel {
    val bids = filter { it.type == BookOrderTypes.BID.id }
        .map {
            it.toDomain()
        }
    val asks = filter { it.type == BookOrderTypes.ASK.id }
        .map {
            it.toDomain()
        }

    return BookOrdersModel(
        bids = bids,
        asks = asks
    )
}

fun BookOrderEntity.toDomain(): BookOrderResumeModel {
    return BookOrderResumeModel(
        book = book,
        amount = amount,
        price = price
    )
}

fun List<BookEntity>.toBookListDomain(): List<BookModel> {
    return map {
        it.toDomain()
    }
}

fun BookEntity.toDomain(): BookModel {
    return BookModel(
        book = book,
        name = name
    )
}

fun BookDetailModel.toEntity(): BookDetailEntity {
    return BookDetailEntity(
        book = book,
        volume = volume,
        high = high,
        last = last,
        low = low
    )
}

fun BookDetailEntity.toDomain(): BookDetailModel {
    return BookDetailModel(
        book = book,
        low = low,
        high = high,
        volume = volume,
        last = last
    )
}

/***
 *  Model transformations
 */
fun List<BookModel>.toBookEntityList(): List<BookEntity> {
    return map {
        it.toEntity()
    }
}

fun BookModel.toEntity(): BookEntity {
    return BookEntity(
        book = book,
        name = name
    )
}

fun BookOrdersModel.toEntityList(): List<BookOrderEntity> {
    val askEntities = this.asks.map {
        it.toEntity(BookOrderTypes.ASK)
    }
    val bidsEntities = this.bids.map {
        it.toEntity(BookOrderTypes.BID)
    }
    return askEntities.union(bidsEntities).toList()
}

fun BookOrderResumeModel.toEntity(type: BookOrderTypes): BookOrderEntity {
    return BookOrderEntity(
        book = book,
        price = price,
        amount = amount,
        type = type.id
    )
}
