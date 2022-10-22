package tech.ericwathome.tours.domain.use_case

import kotlinx.coroutines.flow.flow
import tech.ericwathome.tours.domain.model.Photo
import tech.ericwathome.tours.domain.repository.Repository
import tech.ericwathome.tours.util.Resource
import java.io.IOException
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(photo: Photo) = flow {
        try {
            emit(Resource.Loading())
            repository.deletePhoto(photo)
            emit(Resource.Success("Photo deleted successfully"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "unable to delete photo"))
        }
    }
}