package tech.ericwathome.scenes.domain.repository

import tech.ericwathome.scenes.domain.model.Photo

interface Repository {
    suspend fun allPhotos(page: Int): List<Photo>

    suspend fun bookmarkedPhotos(): List<Photo>

    suspend fun addToBookmarks(photo: Photo)

    suspend fun deletePhoto(photo: Photo)
}