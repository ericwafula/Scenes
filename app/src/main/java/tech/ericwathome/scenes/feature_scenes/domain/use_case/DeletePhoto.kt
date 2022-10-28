package tech.ericwathome.scenes.feature_scenes.domain.use_case

import kotlinx.coroutines.flow.flow
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.scenes.feature_scenes.domain.repository.Repository
import tech.ericwathome.scenes.util.Resource
import java.io.IOException
import javax.inject.Inject

class DeletePhoto @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(photo: Photo) {
        repository.deletePhoto(photo)
    }
}