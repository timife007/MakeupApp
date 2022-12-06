package com.timife.makeup.presentation.makeupBrands

import com.timife.domain.model.MakeupItem

//sealed class MakeupState {
//    data class Success(val items: List<MakeupItem> = emptyList()) : MakeupState()
//    data class Error(val exception: String): MakeupState()
//    object Loading: MakeupState()
//}

data class MakeupState(
    val data : List<MakeupItem> = emptyList(),
    val isLoading:Boolean = false,
    val error:String = ""
)
sealed interface MakeupUiState {
    data class Success(val makeupItems: List<MakeupItem>) : MakeupUiState
    data class Error(val errorMessage: String) : MakeupUiState
    object Loading : MakeupUiState
}