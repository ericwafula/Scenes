package tech.ericwathome.scenes.feature_scenes.data.repository

import kotlinx.coroutines.flow.Flow
import tech.ericwathome.scenes.feature_scenes.data.data_source.local.PhotoDao
import tech.ericwathome.scenes.feature_scenes.data.data_source.remote.UnsplashApiService
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.scenes.feature_scenes.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: UnsplashApiService,
    private val photoDao: PhotoDao
) :
    Repository {
    override suspend fun allPhotos(page: Int): List<Photo> {
        return apiService.getPhotos(page)
    }

    override fun bookmarkedPhotos(): Flow<List<Photo>> {
        return photoDao.savedPhotos()
    }

    override suspend fun addToBookmarks(photo: Photo) {
        photoDao.insert(photo)
    }

    override suspend fun deletePhoto(photo: Photo) {
        photoDao.delete(photo)
    }

}