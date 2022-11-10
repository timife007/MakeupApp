package com.timife.domain.repositories

import com.timife.domain.model.Brand
import com.timife.domain.Resource
import kotlinx.coroutines.flow.Flow

interface BrandRepository {
    suspend fun getBrands(fetchFromRemote: Boolean): Flow<Resource<List<Brand>>>
}