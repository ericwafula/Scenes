package tech.ericwathome.tours.data

import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.Resource

interface Repository {
    suspend fun allPhotos(): Resource<List<Photo>>
}