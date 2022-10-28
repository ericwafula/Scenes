package tech.ericwathome.scenes.feature_scenes.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.ericwathome.scenes.feature_scenes.data.data_source.remote.UnsplashPagingSource
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.scenes.feature_scenes.domain.repository.Repository
import tech.ericwathome.scenes.util.NETWORK_PAGE_SIZE
import javax.inject.Inject

class AllPhotos @Inject constructor(
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