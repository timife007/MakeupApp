package com.timife.domain.use_cases

import com.timife.domain.model.Brand
import com.timife.domain.repositories.BrandRepository
import com.timife.domain.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBrands @Inject constructor(
    private val repository: BrandRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<Resource<List<Brand>>> {
        return repository.getBrands(fetchFromRemote)
    }
}