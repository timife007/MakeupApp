package com.timife.remote

import com.timife.remote.model.MakeupListItemDto
import retrofit2.http.GET

interface MakeupApi {
    @GET("products.json/")
    suspend fun getMakeupBrands(): List<MakeupListItemDto>

    @GET("products.json/")
    suspend fun getMakeupItems(): List<MakeupListItemDto>
}