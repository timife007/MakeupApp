package com.timife.makeup.data.remote

import com.timife.makeup.data.remote.model.MakeupListItemDto
import retrofit2.http.GET

interface MakeupApi {
    @GET("products.json/")
    suspend fun getMakeupBrands(): List<MakeupListItemDto>

    @GET("products.json/")
    suspend fun getMakeupItems(): List<MakeupListItemDto>
}