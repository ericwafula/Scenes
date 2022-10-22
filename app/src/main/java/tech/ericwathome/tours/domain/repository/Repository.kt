package tech.ericwathome.tours.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import tech.ericwathome.tours.domain.model.Photo
import tech.ericwathome.tours.util.Resource

interface Repository {
    suspend fun allPhotos(page: Int): List<Photo>

    suspend fun bookmarkedPhotos(): List<Photo>

    suspend fun addToBookmarks(photo: Photo)

    suspend fun deletePhoto(photo: Photo)
}