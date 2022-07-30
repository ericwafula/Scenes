package tech.ericwathome.tours.model.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import tech.ericwathome.tours.repository.Repository
import tech.ericwathome.tours.model.Photo
import javax.inject.Inject

@HiltViewModel
class ScenesFragmentViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {
    private var _photos : MutableStateFlow<PagingData<Photo>?> = MutableStateFlow(null)
    val photos = _photos.asStateFlow()

    init {
        allPhotos()
    }

    fun allPhotos() {
        viewModelScope.launch {
            repository.allPhotos()
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
                .collect {
                    _photos.value = it
                }
        }
    }
}