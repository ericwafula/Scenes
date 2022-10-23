package tech.ericwathome.scenes.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import tech.ericwathome.scenes.domain.model.Photo

interface UnsplashApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") query: Int
    ): List<Photo>
}