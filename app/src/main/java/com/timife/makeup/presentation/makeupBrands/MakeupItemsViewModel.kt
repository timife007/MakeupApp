package com.timife.makeup.presentation.makeupBrands

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.makeup.domain.model.Brand
import com.timife.makeup.domain.model.MakeupItem
import com.timife.makeup.domain.use_cases.MakeupBrandsUseCase
import com.timife.makeup.domain.use_cases.MakeupListUseCase
import com.timife.makeup.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakeupItemsViewModel @Inject constructor(
    private val getBrandsUseCase: MakeupBrandsUseCase,
    private val getMakeupListUseCase: MakeupListUseCase
):ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
    get() = _loading

    private val _itemLoading = MutableLiveData<Boolean>()
    val itemLoading: LiveData<Boolean>
        get() = _itemLoading

    private val _brandData = MutableLiveData<Resource<List<Brand>>>()
    val brandData : LiveData<Resource<List<Brand>>>
    get() = _brandData

    private val _itemsData = MutableLiveData<Resource<List<MakeupItem>>>()
    val itemsData : LiveData<Resource<List<MakeupItem>>>
    get() = _itemsData

    private val _navigateToSelectedItem = MutableLiveData<MakeupItem?>()
    val navigateToSelectedItem: LiveData<MakeupItem?>
    get() = _navigateToSelectedItem

    init {
        getBrands()
        getMakeupItems()
    }

    fun getMakeupItems(fetchFromRemote: Boolean = false,brand:String = ""){
        viewModelScope.launch {
                getMakeupListUseCase.invoke(fetchFromRemote,brand).collect { resource->
                    when(resource){
                        is Resource.Success ->{
                            _itemsData.value = resource
                        }
                        is Resource.Loading ->{
                            _itemLoading.value = resource.isLoading
                        }

                        is Resource.Error ->Unit
                    }
                }
        }
    }
    fun getAllMakeupItems(fetchFromRemote: Boolean = false){
        viewModelScope.launch {
            getMakeupListUseCase.getAllItems().collect { resource->
                when(resource){
                    is Resource.Success ->{
                        _itemsData.value = resource
                    }
                    is Resource.Loading ->{
                        _itemLoading.value = resource.isLoading
                    }

                    is Resource.Error ->Unit
                }
            }
        }
    }


     private fun getBrands(fetchFromRemote:Boolean = false){
        viewModelScope.launch {
            getBrandsUseCase(fetchFromRemote).collect { resource->
                when(resource){
                    is Resource.Success ->{
                        _brandData.value = resource
                    }
                    is Resource.Loading ->{
                        _loading.value = resource.isLoading
                    }

                    is Resource.Error ->Unit
                }
            }
        }
    }

    fun displayMakeupItems(product:MakeupItem){
        _navigateToSelectedItem.value = product
    }

    //To end navigation, and prompt navigateUp.
    fun displayBrandItemsComplete(){
        _navigateToSelectedItem.value = null
    }
}