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
    override fun allPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(apiService) }
        ).flow
    }

    override fun bookmarkedPhotos(): Flow<Photo> {
        return flow {
            val photos = photoDao.savedPhotos()
            photos.forEach { photo -> emit(photo) }
        }.flowOn(Dispatchers.IO)
    }

    override fun addToBookmarks(photo: Photo) {
        CoroutineScope(Dispatchers.IO).launch {
            photoDao.insert(photo)
        }
    }

    override fun deletePhoto(photo: Photo) {
        CoroutineScope(Dispatchers.IO).launch {
            photoDao.delete(photo)
        }
    }

}