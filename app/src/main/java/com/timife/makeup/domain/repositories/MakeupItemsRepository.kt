package com.timife.makeup.domain.repositories

import com.timife.makeup.data.local.model.MakeupItemEntity
import com.timife.makeup.data.remote.model.MakeupListItemDto
import com.timife.makeup.domain.model.Brand
import com.timife.makeup.domain.model.MakeupItem
import com.timife.makeup.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MakeupItemsRepository {
    suspend fun getMakeupItems(fetchFromRemote: Boolean,brand:String): Flow<Resource<List<MakeupItem>>>

    suspend fun getAllMakeupItems(): Flow<Resource<List<MakeupItem>>>
}