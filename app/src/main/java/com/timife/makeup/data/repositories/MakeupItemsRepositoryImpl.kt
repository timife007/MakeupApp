package com.timife.makeup.data.repositories

import com.timife.makeup.data.local.database.MakeupItemDao
import com.timife.makeup.data.local.model.MakeupItemEntity
import com.timife.makeup.data.remote.MakeupApi
import com.timife.makeup.data.remote.model.MakeupListItemDto
import com.timife.makeup.domain.repositories.MakeupItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MakeupItemsRepositoryImpl @Inject constructor(
    private val makeupApi: MakeupApi,
    private val makeupItemDao: MakeupItemDao
    ):MakeupItemsRepository {
    override suspend fun getLocalMakeupItems(brand: String): List<MakeupItemEntity> {
        return withContext(Dispatchers.IO){
            makeupItemDao.getAllMakeupItems(brand)
        }
    }

    override suspend fun getUnfilteredItems(): List<MakeupItemEntity> {
        return withContext(Dispatchers.IO){
            makeupItemDao.getMakeupItems()
        }
    }

    override suspend fun getRemoteMakeupItems(): List<MakeupListItemDto> {
        return makeupApi.getMakeupItems()
    }

    override suspend fun insertItem(list: List<MakeupItemEntity>) {
        makeupItemDao.insertMakeupItems(list)
    }

    override suspend fun clearItemList() {
        makeupItemDao.clearAllMakeupItems()
    }
}