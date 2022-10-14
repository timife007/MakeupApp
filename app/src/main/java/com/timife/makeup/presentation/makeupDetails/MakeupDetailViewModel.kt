package com.timife.makeup.presentation.makeupDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.timife.domain.model.MakeupItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakeupDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) :ViewModel(){

    private val _itemData = MutableLiveData<MakeupItem>()
    val itemData : LiveData<MakeupItem>
        get() = _itemData

    init {
        val makeup = savedStateHandle.get<MakeupItem>("selectedMakeup")
        _itemData.value = makeup ?: MakeupItem(
            1,
            "",
            "",
            "",
            "",
            "",
            0.0,
            "",
            "",
            ""
        )
    }
}