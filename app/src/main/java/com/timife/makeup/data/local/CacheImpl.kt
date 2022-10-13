package com.timife.makeup.data.local

import com.timife.makeup.data.local.database.BrandDao
import com.timife.makeup.data.local.database.MakeupItemDao
import com.timife.makeup.data.local.model.MakeupBrandEntity
import com.timife.makeup.data.local.model.MakeupItemEntity
import javax.inject.Inject

class CacheImpl @Inject constructor(
    private val makeupItemDao : MakeupItemDao,
    private val makeupBrandDao:BrandDao

) : Cache{
    override suspend fun getLocalMakeupBrands(): List<MakeupBrandEntity> {
        return makeupBrandDao.getAllBrands()
    }

    override suspend fun insertBrand(list: List<MakeupBrandEntity>) {
        makeupBrandDao.insertMakeupBrands(list)
    }

    override suspend fun clearBrand() {
        makeupBrandDao.clearAllBrands()
    }

    override suspend fun getLocalMakeupItems(brand: String): List<MakeupItemEntity> {
        return makeupItemDao.getAllMakeupItems(brand)
    }

    override suspend fun getUnfilteredItems(): List<MakeupItemEntity> {
        return makeupItemDao.getMakeupItems()
    }

    override suspend fun insertItem(list: List<MakeupItemEntity>) {
        makeupItemDao.insertMakeupItems(list)
    }

    override suspend fun clearItemList() {
        makeupItemDao.clearAllMakeupItems()
    }

}