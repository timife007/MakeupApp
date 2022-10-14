package com.timife.domain.use_cases

import com.timife.domain.model.MakeupItem
import com.timife.domain.repositories.MakeupItemsRepository
import com.timife.domain.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMakeupItems @Inject constructor(
    private val repository: MakeupItemsRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean,brand:String): Flow<Resource<List<MakeupItem>>> {
        return repository.getMakeupItems(fetchFromRemote,brand)
    }
}
