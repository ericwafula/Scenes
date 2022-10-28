package tech.ericwathome.scenes.feature_scenes.data.data_source.remote

import retrofit2.http.GET
import retrofit2.http.Query
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo

interface UnsplashApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") query: Int
    ): List<Photo>
}