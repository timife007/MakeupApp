package com.timife.domain.use_cases

import com.timife.domain.Resource
import com.timife.domain.model.Brand
import com.timife.domain.repositories.BrandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BrandFakeRepo : BrandRepository {
    private val brandList = (1..10).map {
        Brand(
            id = it,
            brand = "brand$it"
        )
    }
    override suspend fun getBrands(fetchFromRemote: Boolean): Flow<Resource<List<Brand>>> {
        return flow {
            emit(Resource.Success(brandList))
        }
    }
}