package com.timife.makeup.data.remote

import com.timife.makeup.data.remote.model.MakeupListItemDto

interface Remote {
    suspend fun getRemoteMakeupBrands(): List<MakeupListItemDto>

    suspend fun getRemoteMakeupItems():List<MakeupListItemDto>

}