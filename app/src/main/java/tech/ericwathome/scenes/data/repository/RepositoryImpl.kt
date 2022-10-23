package tech.ericwathome.scenes.data.repository

import tech.ericwathome.scenes.data.remote.UnsplashApiService
import tech.ericwathome.scenes.data.local.PhotoDao
import tech.ericwathome.scenes.domain.model.Photo
import tech.ericwathome.scenes.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: UnsplashApiService,
    private val photoDao: PhotoDao
) :
    Repository {
    override suspend fun allPhotos(page: Int): List<Photo> {
        return apiService.getPhotos(page)
    }

    override suspend fun bookmarkedPhotos(): List<Photo> {
        return photoDao.savedPhotos()
    }

    override suspend fun addToBookmarks(photo: Photo) {
        photoDao.insert(photo)
    }

    override suspend fun deletePhoto(photo: Photo) {
        photoDao.delete(photo)
    }

}