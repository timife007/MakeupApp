package com.timife.makeup.domain.repositories

import com.timife.makeup.data.local.model.MakeupBrandEntity
import com.timife.makeup.data.local.model.MakeupItemEntity
import com.timife.makeup.data.remote.model.MakeupList
import com.timife.makeup.data.remote.model.MakeupListItemDto
import com.timife.makeup.domain.model.Brand
import com.timife.makeup.domain.model.MakeupItem
import com.timife.makeup.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MakeupBrandRepository {
    suspend fun getRemoteMakeupBrands(): List<MakeupListItemDto>

    suspend fun getLocalMakeupBrands():List<MakeupBrandEntity>

    suspend fun insertBrand(list:List<MakeupBrandEntity>)

    suspend fun clearBrand()
}