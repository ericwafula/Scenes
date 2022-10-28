package tech.ericwathome.scenes.feature_scenes.presentation.scenes

import tech.ericwathome.scenes.feature_scenes.domain.model.Photo

data class ScenesState(
    val photos: List<Photo> = emptyList(),
    val message: String? = null,
    val loading: Boolean = false
)