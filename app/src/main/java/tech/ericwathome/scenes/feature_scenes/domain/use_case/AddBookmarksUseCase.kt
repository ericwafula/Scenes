package tech.ericwathome.scenes.feature_scenes.domain.use_case

import kotlinx.coroutines.flow.flow
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.scenes.feature_scenes.domain.repository.Repository
import tech.ericwathome.scenes.util.Resource
import java.io.IOException
import javax.inject.Inject

class AddBookmarksUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(photo: Photo)  = flow {
        try {
            emit(Resource.Loading())
            repository.addToBookmarks(photo)
            emit(Resource.Success("Photo added successfully"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "unable to bookmark photo"))
        }
    }
}