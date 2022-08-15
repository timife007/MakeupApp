package com.timife.makeup.domain.use_cases

import com.timife.makeup.data.mappers.toBrand
import com.timife.makeup.data.mappers.toMakeupBrand
import com.timife.makeup.data.mappers.toMakeupBrandEntity
import com.timife.makeup.domain.model.Brand
import com.timife.makeup.domain.repositories.MakeupBrandRepository
import com.timife.makeup.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class MakeupBrandsUseCase @Inject constructor(
    private val repository: MakeupBrandRepository
) {
    operator fun invoke(fetchFromRemote: Boolean): Flow<Resource<List<Brand>>> {
        return flow {
            emit(Resource.Loading(true))
            val localBrand = repository.getLocalMakeupBrands()
            if(localBrand.isNotEmpty()){
                emit(Resource.Success(data = localBrand.map {
                    it.toMakeupBrand()
                }))
            }


            //Should only load data from db if Db is not empty and remote fetch is false.
            val isDbEmpty = localBrand.isEmpty()
            val shouldLoadFromDb = !isDbEmpty && !fetchFromRemote
            if (shouldLoadFromDb) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteBrand = try {
                repository.getRemoteMakeupBrands().distinctBy {
                    it.brand
                }.map {
                    it.toBrand()
                }
            } catch (e: IOException) {
                Timber.e(e.message)
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
                null
            } catch (e: HttpException) {
                Timber.e(e.message())
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
                null
            }

            //When new data is available, clear cache and insert new
            remoteBrand?.let { brand ->
                repository.clearBrand()
                repository.insertBrand(
                    brand.map {
                        it.toMakeupBrandEntity()
                    }
                )
                emit(Resource.Success(
                    data = repository.getLocalMakeupBrands().map {
                        it.toMakeupBrand()
                    }
                ))
                emit(
                    Resource.Loading(
                        false
                    )
                )
            }
        }
    }
}