package com.timife.makeup.domain.repositories

import com.timife.makeup.data.local.model.MakeupItemEntity
import com.timife.makeup.data.remote.model.MakeupListItemDto

interface MakeupItemsRepository {
    suspend fun getLocalMakeupItems(brand: String):List<MakeupItemEntity>

    suspend fun getUnfilteredItems():List<MakeupItemEntity>

    suspend fun getRemoteMakeupItems():List<MakeupListItemDto>

    suspend fun insertItem(list:List<MakeupItemEntity>)

    suspend fun clearItemList()
}