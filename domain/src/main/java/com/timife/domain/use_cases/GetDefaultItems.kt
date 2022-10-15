package com.timife.domain.use_cases

import com.timife.domain.model.MakeupItem
import com.timife.domain.repositories.MakeupItemsRepository
import com.timife.domain.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDefaultItems @Inject constructor(
    private val repository: MakeupItemsRepository
) {
     suspend operator fun invoke(): Flow<Resource<List<MakeupItem>>>{
         return repository.getAllMakeupItems()
     }
}
