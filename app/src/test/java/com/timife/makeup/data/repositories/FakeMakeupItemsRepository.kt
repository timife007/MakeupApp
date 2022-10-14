package com.timife.makeup.data.repositories

import androidx.lifecycle.MutableLiveData
import com.timife.cache.model.MakeupItemEntity
import com.timife.remote.model.MakeupListItemDto
import com.timife.domain.repositories.MakeupItemsRepository


class FakeMakeupItemsRepository: com.timife.domain.repositories.MakeupItemsRepository {
    private val makeupItems = mutableListOf<com.timife.cache.model.MakeupItemEntity>()
    private val observableShoppingItems = MutableLiveData<List<com.timife.cache.model.MakeupItemEntity>>(makeupItems)



    //Update livedata at every change in fake database
    private fun refreshLiveData() {
        observableShoppingItems.postValue(makeupItems)
    }

    override suspend fun getLocalMakeupItems(brand: String): List<com.timife.cache.model.MakeupItemEntity> {
        return makeupItems.filter {
            it.brand == brand
        }
    }

    override suspend fun getUnfilteredItems(): List<com.timife.cache.model.MakeupItemEntity> {
        return makeupItems
    }

    override suspend fun getRemoteMakeupItems(): List<com.timife.remote.model.MakeupListItemDto> {
        return emptyList()
    }

    override suspend fun insertItem(list: List<com.timife.cache.model.MakeupItemEntity>) {
        list.forEach{
            makeupItems.add(it)
        }
        refreshLiveData()
    }

    override suspend fun clearItemList() {
        makeupItems.clear()
        refreshLiveData()
    }


}