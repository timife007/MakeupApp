package com.timife.makeup.data.repositories

import androidx.lifecycle.MutableLiveData
import com.timife.cache.model.MakeupItemEntity
import com.timife.domain.Resource
import com.timife.domain.model.MakeupItem
import com.timife.remote.model.MakeupListItemDto
import com.timife.domain.repositories.MakeupItemsRepository
import kotlinx.coroutines.flow.Flow


class FakeMakeupItemsRepository : MakeupItemsRepository {
    override suspend fun getMakeupItems(
        fetchFromRemote: Boolean,
        brand: String
    ): Flow<Resource<List<MakeupItem>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllMakeupItems(): Flow<Resource<List<MakeupItem>>> {
        TODO("Not yet implemented")
    }


}