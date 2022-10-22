package tech.ericwathome.tours.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.ericwathome.tours.data.remote.UnsplashPagingSource
import tech.ericwathome.tours.domain.model.Photo
import tech.ericwathome.tours.domain.repository.Repository
import tech.ericwathome.tours.util.NETWORK_PAGE_SIZE

class AllPhotosUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<PagingData<Photo>> {
        val pagingConfig = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false)
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { UnsplashPagingSource(repository) }
        ).flow
    }
}