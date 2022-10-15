package com.timife.makeup.presentation.makeupBrands

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.domain.model.Brand
import com.timife.domain.model.MakeupItem
import com.timife.domain.use_cases.GetBrands
import com.timife.domain.use_cases.GetDefaultItems
import com.timife.domain.use_cases.GetMakeupItems
import com.timife.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakeupItemsViewModel @Inject constructor(
    private val getBrandsUseCase: GetBrands,
    private val getItems: GetMakeupItems,
    private val getDefaultItems: GetDefaultItems
):ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
    get() = _loading

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
                 getItems(fetchFromRemote,brand).collect{ resource->
                    when(resource){
                        is Resource.Success ->{
                            _itemsData.value = resource
                        }
                        is Resource.Loading ->{
                            _loading.value = resource.isLoading
                        }

                        is Resource.Error -> {
                            _itemsData.value = Resource.Error(message = resource.message,data = resource.data)
                        }
                    }
                }
        }
    }
    fun getAllMakeupItems(){
        viewModelScope.launch {
            getDefaultItems().collect { resource->
                when(resource){
                    is Resource.Success ->{
                        _itemsData.value = resource
                    }
                    is Resource.Loading ->{
                        _loading.value = resource.isLoading
                    }

                    is Resource.Error ->{
                        _itemsData.value = Resource.Error(message = resource.message, data = resource.data)
                    }
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

                    is Resource.Error -> {
                        _brandData.value = Resource.Error(data = resource.data, message = resource.message)
                    }
                }
            }
        }
    }

    fun displayMakeupItems(product: MakeupItem){
        _navigateToSelectedItem.value = product
    }

    //To end navigation, and prompt navigateUp.
    fun displayBrandItemsComplete(){
        _navigateToSelectedItem.value = null
    }
}