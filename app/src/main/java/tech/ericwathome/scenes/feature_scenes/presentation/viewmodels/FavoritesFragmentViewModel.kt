package tech.ericwathome.scenes.feature_scenes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.scenes.feature_scenes.domain.use_case.BookmarkedPhotos
import tech.ericwathome.scenes.feature_scenes.domain.use_case.DeletePhoto
import tech.ericwathome.scenes.util.LoadState
import tech.ericwathome.scenes.util.Resource
import javax.inject.Inject

@HiltViewModel
class FavoritesFragmentViewModel @Inject constructor(
    private val bookmarkedPhotosUseCase: BookmarkedPhotos,
    private val deletePhoto: DeletePhoto
) : ViewModel() {
    private var _bookmarkedPhotos = MutableStateFlow(listOf<Photo>())
    val bookmarkedPhotos = _bookmarkedPhotos.asStateFlow()
    private val deletePhotosState = MutableStateFlow(LoadState<Any>())

    init {
        getBookmarkedPhotos()
    }

    private fun getBookmarkedPhotos() {
        viewModelScope.launch {
            bookmarkedPhotosUseCase()
                .collect {

                }
        }
    }

    fun deletePhoto(photo: Photo) {
        
    }
}