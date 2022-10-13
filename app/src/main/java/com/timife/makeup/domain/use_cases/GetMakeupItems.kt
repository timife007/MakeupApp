package com.timife.makeup.domain.use_cases

import com.timife.makeup.data.mappers.toMakeupItem
import com.timife.makeup.data.mappers.toMakeupItemEntity
import com.timife.makeup.domain.model.MakeupItem
import com.timife.makeup.domain.repositories.MakeupItemsRepository
import com.timife.makeup.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GetMakeupItems @Inject constructor(
    private val repository: MakeupItemsRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean,brand:String): Flow<Resource<List<MakeupItem>>> {
        return repository.getMakeupItems(fetchFromRemote,brand)
    }
}
