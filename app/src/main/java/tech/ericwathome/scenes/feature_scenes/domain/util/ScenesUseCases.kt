package tech.ericwathome.scenes.feature_scenes.domain.util

import tech.ericwathome.scenes.feature_scenes.domain.use_case.AddBookmarks
import tech.ericwathome.scenes.feature_scenes.domain.use_case.AllPhotos
import tech.ericwathome.scenes.feature_scenes.domain.use_case.BookmarkedPhotos
import tech.ericwathome.scenes.feature_scenes.domain.use_case.DeletePhoto

data class ScenesUseCases(
    val addBookmarks: AddBookmarks,
    val allPhotos: AllPhotos,
    val bookmarkedPhotos: BookmarkedPhotos,
    val deletePhoto: DeletePhoto
)