package tech.ericwathome.tours.data

import tech.ericwathome.tours.data.network.UnsplashApiService
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.Resource
import javax.inject.Inject

class RepositoryImpl @Inject constructor (val unsplashApiService: UnsplashApiService) : Repository {
    override suspend fun allPhotos(): Resource<List<Photo>> {
        TODO("Not yet implemented")
    }

}