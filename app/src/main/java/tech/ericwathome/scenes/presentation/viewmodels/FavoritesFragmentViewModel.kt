package tech.ericwathome.scenes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tech.ericwathome.scenes.domain.model.Photo
import tech.ericwathome.scenes.domain.use_case.BookmarkedPhotosUseCase
import tech.ericwathome.scenes.domain.use_case.DeletePhotoUseCase
import tech.ericwathome.scenes.util.LoadState
import tech.ericwathome.scenes.util.Resource
import javax.inject.Inject

@HiltViewModel
class FavoritesFragmentViewModel @Inject constructor(
    private val bookmarkedPhotosUseCase: BookmarkedPhotosUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase
) : ViewModel() {
    private var _bookmarkedPhotos= MutableStateFlow(listOf<Photo>())
    val bookmarkedPhotos = _bookmarkedPhotos.asStateFlow()
    val deletePhotosState = MutableStateFlow(LoadState<Any>())

    init {
        getBookmarkedPhotos()
    }

    private fun getBookmarkedPhotos() {
        viewModelScope.launch {
            bookmarkedPhotosUseCase()
                .collect {
                    _bookmarkedPhotos.value = it
                }
        }
    }

    fun deletePhoto(photo: Photo) {
        deletePhotoUseCase(photo).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    deletePhotosState.value = LoadState(data = result.data)
                }
                is Resource.Loading -> {
                    deletePhotosState.value = LoadState(loading = true)
                }
                is Resource.Error -> {
                    deletePhotosState.value = LoadState(errorMessage = "An unknown error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}