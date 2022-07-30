package tech.ericwathome.tours.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.ericwathome.tours.data.UnsplashPagingSource
import tech.ericwathome.tours.data.network.UnsplashApiService
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.NETWORK_PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

class RepositoryImpl @Inject constructor (private val apiService: UnsplashApiService) :
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

}