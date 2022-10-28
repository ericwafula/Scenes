package tech.ericwathome.scenes.feature_scenes.domain.use_case

import kotlinx.coroutines.flow.Flow
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.scenes.feature_scenes.domain.repository.Repository
import javax.inject.Inject

class BookmarkedPhotos @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Photo>> {
        return repository.bookmarkedPhotos()
    }
}