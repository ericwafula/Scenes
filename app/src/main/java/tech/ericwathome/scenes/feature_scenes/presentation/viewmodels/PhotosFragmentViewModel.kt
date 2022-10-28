package tech.ericwathome.scenes.feature_scenes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.scenes.feature_scenes.domain.use_case.AddBookmarks
import tech.ericwathome.scenes.feature_scenes.domain.use_case.AllPhotos
import tech.ericwathome.scenes.util.LoadState
import tech.ericwathome.scenes.util.Resource
import javax.inject.Inject

@HiltViewModel
class PhotosFragmentViewModel @Inject constructor(
    private val allPhotos: AllPhotos,
    private val addBookmarks: AddBookmarks
) :
    ViewModel() {
    private var _photos: MutableStateFlow<PagingData<Photo>?> = MutableStateFlow(null)
    val photos = _photos.asStateFlow()
    val bookmarksState = MutableStateFlow(LoadState<Any>())

    init {
        allPhotos()
    }

    private fun allPhotos() {
        viewModelScope.launch {

        }
    }

    fun addToBookmarks(photo: Photo) {

    }
}