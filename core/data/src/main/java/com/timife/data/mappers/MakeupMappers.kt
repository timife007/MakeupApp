package com.timife.data.mappers

import com.timife.cache.model.MakeupBrandEntity
import com.timife.cache.model.MakeupItemEntity
import com.timife.domain.model.Brand
import com.timife.domain.model.MakeupItem
import com.timife.remote.model.MakeupListItemDto

fun MakeupBrandEntity.toMakeupBrand(): Brand {
    return Brand(
        id = id ?: 0,
        brand = brand
    )
}

fun Brand.toMakeupBrandEntity(): MakeupBrandEntity {
    return MakeupBrandEntity(
        id = id,
        brand = brand
    )
}

fun MakeupListItemDto.toBrand(): Brand {
    return Brand(
        id = id ?: 0,
        brand = brand ?: ""
    )
}

fun MakeupItem.toMakeupItemEntity(): MakeupItemEntity {
    return MakeupItemEntity(
        id = id,
        name = name,
        imageUrl = imageLink,
        price = price,
        priceSign = priceSign,
        rating = rating,
        currency = currency,
        productType = productType,
        description = description,
        brand = brand
    )
}

fun MakeupItemEntity.toMakeupItem(): MakeupItem {
    return MakeupItem(
        id = id ?: 0,
        name = name,
        imageLink = imageUrl,
        price = price,
        priceSign = priceSign,
        rating = rating,
        currency = currency,
        productType = productType,
        description = description,
        brand = brand
    )
}

fun MakeupListItemDto.toMakeupItem(): MakeupItem {
    return MakeupItem(
        id = id ?: 0,
        name = name ?: "",
        description = description ?: "",
        price = price ?: "",
        currency = currency.toString(),
        priceSign = priceSign.toString(),
        imageLink = imageLink ?: "",
        productType = productType ?: "",
        rating = rating ?: 0.0,
        brand = brand ?: ""
    )
}