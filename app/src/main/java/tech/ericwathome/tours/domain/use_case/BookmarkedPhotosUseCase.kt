package tech.ericwathome.tours.domain.use_case

import kotlinx.coroutines.flow.flow
import tech.ericwathome.tours.domain.repository.Repository
import javax.inject.Inject

class BookmarkedPhotosUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() = flow {
        emit(repository.bookmarkedPhotos())
    }
}