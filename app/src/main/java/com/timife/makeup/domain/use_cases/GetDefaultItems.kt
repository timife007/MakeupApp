package com.timife.makeup.domain.use_cases

import com.timife.makeup.domain.model.MakeupItem
import com.timife.makeup.domain.repositories.MakeupItemsRepository
import com.timife.makeup.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDefaultItems @Inject constructor(
    private val repository: MakeupItemsRepository
) {
     suspend operator fun invoke(): Flow<Resource<List<MakeupItem>>>{
         return repository.getAllMakeupItems()
     }
}
