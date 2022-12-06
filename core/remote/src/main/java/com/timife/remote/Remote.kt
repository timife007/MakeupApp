package com.timife.remote

import com.timife.remote.model.MakeupListItemDto

interface Remote {
    suspend fun getRemoteMakeupBrands(): List<MakeupListItemDto>

    suspend fun getRemoteMakeupItems():List<MakeupListItemDto>
}