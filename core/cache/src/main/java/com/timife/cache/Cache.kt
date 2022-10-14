package com.timife.cache

import com.timife.cache.model.MakeupBrandEntity
import com.timife.cache.model.MakeupItemEntity

interface Cache {
    suspend fun getLocalMakeupBrands():List<MakeupBrandEntity>

    suspend fun insertBrand(list:List<MakeupBrandEntity>)

    suspend fun clearBrand()

    suspend fun getLocalMakeupItems(brand: String):List<MakeupItemEntity>

    suspend fun getUnfilteredItems():List<MakeupItemEntity>

    suspend fun insertItem(list:List<MakeupItemEntity>)

    suspend fun clearItemList()

}