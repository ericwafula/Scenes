package tech.ericwathome.scenes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.ericwathome.scenes.domain.model.Photo
import tech.ericwathome.scenes.domain.use_case.AddBookmarksUseCase
import tech.ericwathome.scenes.domain.use_case.AllPhotosUseCase
import tech.ericwathome.scenes.util.LoadState
import tech.ericwathome.scenes.util.Resource
import javax.inject.Inject

@HiltViewModel
class PhotosFragmentViewModel @Inject constructor(
    private val allPhotosUseCase: AllPhotosUseCase,
    private val addBookmarksUseCase: AddBookmarksUseCase
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
            allPhotosUseCase()
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
                .collect {
                    _photos.value = it
                }
        }
    }

    fun addToBookmarks(photo: Photo) {
        addBookmarksUseCase(photo).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    bookmarksState.value = LoadState(data = result.data)
                }
                is Resource.Loading -> {
                    bookmarksState.value = LoadState(loading = true)
                }
                is Resource.Error -> {
                    bookmarksState.value = LoadState(errorMessage = "An unknown error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}