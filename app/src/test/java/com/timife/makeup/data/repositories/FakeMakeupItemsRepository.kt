package com.timife.makeup.data.repositories

import androidx.lifecycle.MutableLiveData
import com.timife.makeup.data.local.model.MakeupItemEntity
import com.timife.makeup.data.remote.model.MakeupListItemDto
import com.timife.makeup.domain.repositories.MakeupItemsRepository
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import javax.inject.Inject


class FakeMakeupItemsRepository:MakeupItemsRepository {
    private val makeupItems = mutableListOf<MakeupItemEntity>()
    private val observableShoppingItems = MutableLiveData<List<MakeupItemEntity>>(makeupItems)



    //Update livedata at every change in fake database
    private fun refreshLiveData() {
        observableShoppingItems.postValue(makeupItems)
    }

    override suspend fun getLocalMakeupItems(brand: String): List<MakeupItemEntity> {
        return makeupItems.filter {
            it.brand == brand
        }
    }

    override suspend fun getUnfilteredItems(): List<MakeupItemEntity> {
        return makeupItems
    }

    override suspend fun getRemoteMakeupItems(): List<MakeupListItemDto> {
        return emptyList()
    }

    override suspend fun insertItem(list: List<MakeupItemEntity>) {
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