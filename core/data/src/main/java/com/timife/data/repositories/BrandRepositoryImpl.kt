package com.timife.data.repositories

import com.timife.cache.Cache
import com.timife.data.mappers.toBrand
import com.timife.data.mappers.toMakeupBrand
import com.timife.data.mappers.toMakeupBrandEntity
import com.timife.domain.Resource
import com.timife.domain.repositories.BrandRepository
import com.timife.remote.Remote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(
    private val remote: Remote,
    private val cache: Cache
) : BrandRepository {
    override suspend fun getBrands(fetchFromRemote: Boolean): Flow<Resource<List<com.timife.domain.model.Brand>>> {
        return flow {
            emit(Resource.Loading(true))
            val localBrand = cache.getLocalMakeupBrands()
            if (localBrand.isNotEmpty()) {
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
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
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
                emit(
                    Resource.Success(
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