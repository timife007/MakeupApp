package com.timife.domain.use_cases

import com.timife.domain.Resource
import com.timife.domain.model.MakeupItem
import com.timife.domain.repositories.MakeupItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MakeupItemFakeRepo: MakeupItemsRepository {
    private val list = (1..10).map {
        MakeupItem(
            id = it,
            "",
            "",
            "",
            "",
            "",
            2.0,
            brand = "brand$it",
            "",
            ""
        )
    }
    override suspend fun getMakeupItems(
        fetchFromRemote: Boolean,
        brand: String
    ): Flow<Resource<List<MakeupItem>>> {
        return flow{
            val items = list.filter{
                it.brand == brand
            }
            emit(Resource.Success(items))
        }
    }

    override suspend fun getAllMakeupItems(): Flow<Resource<List<MakeupItem>>> {
        return flow {
            emit(Resource.Success(list))
        }
    }
}