package tech.ericwathome.tours.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import tech.ericwathome.tours.domain.model.Photo
import tech.ericwathome.tours.util.Resource

interface Repository {
    fun allPhotos(): Flow<PagingData<Photo>>

    fun bookmarkedPhotos(): Flow<Photo>

    fun addToBookmarks(photo: Photo)

    fun deletePhoto(photo: Photo)
}