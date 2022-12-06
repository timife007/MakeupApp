package com.timife.cache

import com.timife.cache.model.MakeupBrandEntity
import com.timife.cache.model.MakeupItemEntity
import org.junit.Assert.*

import org.junit.Test

class CacheFake : Cache {

    private var makeupItems = emptyList<MakeupItemEntity>()
    private var brands = emptyList<MakeupBrandEntity>()
    override suspend fun getLocalMakeupBrands(): List<MakeupBrandEntity> {
        return brands
    }

    override suspend fun insertBrand(list: List<MakeupBrandEntity>) {
        brands = brands + list
    }

    override suspend fun clearBrand() {
        brands = emptyList()
    }

    override suspend fun getLocalMakeupItems(brand: String): List<MakeupItemEntity> {
        return makeupItems.filter {
            it.brand == brand
        }
    }

    override suspend fun getUnfilteredItems(): List<MakeupItemEntity> {
        return makeupItems
    }

    override suspend fun insertItem(list: List<MakeupItemEntity>) {
        makeupItems = makeupItems + list
    }

    override suspend fun clearItemList() {
        makeupItems = emptyList()
    }
}