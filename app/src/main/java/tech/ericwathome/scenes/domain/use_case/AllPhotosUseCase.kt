package tech.ericwathome.scenes.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.ericwathome.scenes.data.remote.UnsplashPagingSource
import tech.ericwathome.scenes.domain.model.Photo
import tech.ericwathome.scenes.domain.repository.Repository
import tech.ericwathome.scenes.util.NETWORK_PAGE_SIZE
import javax.inject.Inject

class AllPhotosUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Photo>> {
        val pagingConfig = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false)
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { UnsplashPagingSource(repository) }
        ).flow
    }
}