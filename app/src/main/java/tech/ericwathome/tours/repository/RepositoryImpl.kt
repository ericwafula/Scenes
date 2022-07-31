package tech.ericwathome.tours.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.ericwathome.tours.data.UnsplashPagingSource
import tech.ericwathome.tours.data.network.UnsplashApiService
import tech.ericwathome.tours.data.room.PhotoDao
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.NETWORK_PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

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

    override fun bookmarkedPhotos(): Flow<List<Photo>> {
        TODO("Not yet implemented")
    }

    override fun addToBookmarks(photo: Photo) {
        TODO("Not yet implemented")
    }

}