package tech.ericwathome.tours.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import tech.ericwathome.tours.domain.model.Photo

interface UnsplashApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") query: Int
    ): List<Photo>
}