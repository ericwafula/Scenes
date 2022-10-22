package tech.ericwathome.tours.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.ericwathome.tours.data.remote.UnsplashPagingSource
import tech.ericwathome.tours.data.remote.UnsplashApiService
import tech.ericwathome.tours.data.local.PhotoDao
import tech.ericwathome.tours.domain.model.Photo
import tech.ericwathome.tours.domain.repository.Repository
import tech.ericwathome.tours.util.NETWORK_PAGE_SIZE
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: UnsplashApiService,
    private val photoDao: PhotoDao
) :
    Repository {
    override suspend fun allPhotos(page: Int): List<Photo> {
        return apiService.getPhotos(page)
    }

    override suspend fun bookmarkedPhotos(): List<Photo> {
        return photoDao.savedPhotos()
    }

    override suspend fun addToBookmarks(photo: Photo) {
        photoDao.insert(photo)
    }

    override suspend fun deletePhoto(photo: Photo) {
        photoDao.delete(photo)
    }

}