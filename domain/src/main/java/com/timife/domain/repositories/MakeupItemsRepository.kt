package com.timife.domain.repositories

import com.timife.domain.model.MakeupItem
import com.timife.domain.Resource
import kotlinx.coroutines.flow.Flow

interface MakeupItemsRepository {
    suspend fun getMakeupItems(fetchFromRemote: Boolean,brand:String): Flow<Resource<List<MakeupItem>>>

    suspend fun getAllMakeupItems(): Flow<Resource<List<MakeupItem>>>
}
