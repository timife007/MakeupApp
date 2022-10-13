package com.timife.makeup.data.repositories

import com.timife.makeup.data.local.Cache
import com.timife.makeup.data.local.database.MakeupItemDao
import com.timife.makeup.data.local.model.MakeupItemEntity
import com.timife.makeup.data.mappers.toMakeupItem
import com.timife.makeup.data.mappers.toMakeupItemEntity
import com.timife.makeup.data.remote.MakeupApi
import com.timife.makeup.data.remote.Remote
import com.timife.makeup.data.remote.model.MakeupListItemDto
import com.timife.makeup.domain.model.MakeupItem
import com.timife.makeup.domain.repositories.MakeupItemsRepository
import com.timife.makeup.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class MakeupItemsRepositoryImpl @Inject constructor(
    private val cache: Cache,
    private val remote:Remote
):MakeupItemsRepository {
    override suspend fun getMakeupItems(
        fetchFromRemote: Boolean,
        brand: String
    ): Flow<Resource<List<MakeupItem>>> {
        return flow {
            emit(Resource.Loading(true))
            val localItems = cache.getLocalMakeupItems(brand)
            if(localItems.isNotEmpty()) {
                emit(Resource.Success(data = localItems.map {
                    it.toMakeupItem()
                }))
            }

            //Should only load data from db if Db is not empty and remote fetch is false.
            val isDbEmpty = localItems.isEmpty() && brand.isBlank()
            val shouldLoadFromDb = !isDbEmpty && !fetchFromRemote
            if (shouldLoadFromDb) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteItem = try {
                remote.getRemoteMakeupItems().map {
                    it.toMakeupItem()
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
            remoteItem?.let { item ->
                cache.clearItemList()
                cache.insertItem(
                    item.map {
                        it.toMakeupItemEntity()
                    }
                )
                emit(Resource.Success(
                    data = cache.getUnfilteredItems().map {
                        it.toMakeupItem()
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

    override suspend fun getAllMakeupItems(): Flow<Resource<List<MakeupItem>>> {
        return flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(
                data = cache.getUnfilteredItems().map {
                    it.toMakeupItem()
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