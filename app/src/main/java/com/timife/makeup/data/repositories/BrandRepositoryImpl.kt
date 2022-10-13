package com.timife.makeup.data.repositories

import com.timife.makeup.data.local.Cache
import com.timife.makeup.data.mappers.toBrand
import com.timife.makeup.data.mappers.toMakeupBrand
import com.timife.makeup.data.mappers.toMakeupBrandEntity
import com.timife.makeup.data.remote.Remote
import com.timife.makeup.domain.model.Brand
import com.timife.makeup.domain.repositories.BrandRepository
import com.timife.makeup.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(
    private val remote:Remote,
    private val cache: Cache
) : BrandRepository {
    override suspend fun getBrands(fetchFromRemote: Boolean): Flow<Resource<List<Brand>>> {
        return flow {
            emit(Resource.Loading(true))
            val localBrand = cache.getLocalMakeupBrands()
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
                remote.getRemoteMakeupBrands().distinctBy {
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
                cache.clearBrand()
                cache.insertBrand(
                    brand.map {
                        it.toMakeupBrandEntity()
                    }
                )
                emit(Resource.Success(
                    data = cache.getLocalMakeupBrands().map {
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