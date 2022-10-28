package tech.ericwathome.scenes.feature_scenes.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo

interface Repository {
    suspend fun allPhotos(page: Int): List<Photo>

    fun bookmarkedPhotos(): Flow<List<Photo>>

    suspend fun addToBookmarks(photo: Photo)

    suspend fun deletePhoto(photo: Photo)
}