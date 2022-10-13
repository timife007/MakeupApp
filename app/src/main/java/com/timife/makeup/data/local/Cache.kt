package com.timife.makeup.data.local

import com.timife.makeup.data.local.model.MakeupBrandEntity
import com.timife.makeup.data.local.model.MakeupItemEntity
import com.timife.makeup.data.remote.model.MakeupListItemDto

interface Cache {
    suspend fun getLocalMakeupBrands():List<MakeupBrandEntity>

    suspend fun insertBrand(list:List<MakeupBrandEntity>)

    suspend fun clearBrand()

    suspend fun getLocalMakeupItems(brand: String):List<MakeupItemEntity>

    suspend fun getUnfilteredItems():List<MakeupItemEntity>

    suspend fun insertItem(list:List<MakeupItemEntity>)

    suspend fun clearItemList()

}