package com.timife.makeup.domain.use_cases

import com.timife.makeup.domain.model.Brand
import com.timife.makeup.domain.repositories.BrandRepository
import com.timife.makeup.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBrands @Inject constructor(
    private val repository: BrandRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<Resource<List<Brand>>> {
        return repository.getBrands(fetchFromRemote)
    }
}