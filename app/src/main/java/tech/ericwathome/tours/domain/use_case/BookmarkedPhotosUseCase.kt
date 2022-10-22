package tech.ericwathome.tours.domain.use_case

import kotlinx.coroutines.flow.flow
import tech.ericwathome.tours.domain.repository.Repository

class BookmarkedPhotosUseCase(
    private val repository: Repository
) {
    operator fun invoke() = flow {
        emit(repository.bookmarkedPhotos())
    }
}