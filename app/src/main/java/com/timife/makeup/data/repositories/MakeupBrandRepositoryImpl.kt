package com.timife.makeup.data.repositories

import com.timife.makeup.data.local.database.MakeupDao
import com.timife.makeup.data.local.model.MakeupBrandEntity
import com.timife.makeup.data.remote.MakeupApi
import com.timife.makeup.data.remote.model.MakeupListItemDto
import com.timife.makeup.domain.repositories.MakeupBrandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MakeupBrandRepositoryImpl @Inject constructor(
    private val dao: MakeupDao,
    private val api: MakeupApi
) : MakeupBrandRepository {

    override suspend fun getRemoteMakeupBrands(): List<MakeupListItemDto> {
        return api.getMakeupBrands()
    }

    override suspend fun getLocalMakeupBrands(): List<MakeupBrandEntity> {
        return withContext(Dispatchers.IO){
            dao.getAllBrands()
        }
    }

    override suspend fun insertBrand(list: List<MakeupBrandEntity>) {
        dao.insertMakeupBrands(list)
    }

    override suspend fun clearBrand() {
        dao.clearAllBrands()
    }

}