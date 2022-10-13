package com.timife.makeup.data.remote

import com.timife.makeup.data.remote.model.MakeupListItemDto
import javax.inject.Inject

class RemoteImpl @Inject constructor(
   private val api:MakeupApi
): Remote{
    override suspend fun getRemoteMakeupBrands(): List<MakeupListItemDto> {
        return api.getMakeupBrands()
    }

    override suspend fun getRemoteMakeupItems(): List<MakeupListItemDto> {
        return api.getMakeupItems()
    }

}