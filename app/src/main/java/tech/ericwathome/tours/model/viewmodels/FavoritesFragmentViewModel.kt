package tech.ericwathome.tours.model.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.repository.Repository
import javax.inject.Inject

@HiltViewModel
class FavoritesFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var _bookmarkedPhotos: MutableStateFlow<Photo?> = MutableStateFlow(null)
    val bookmarkedPhotos = _bookmarkedPhotos.asStateFlow()

    init {
        getBookmarkedPhotos()
    }

    private fun getBookmarkedPhotos() {
        viewModelScope.launch {
            repository.bookmarkedPhotos()
                .collect {
                    _bookmarkedPhotos.value = it
                }
        }
    }
}