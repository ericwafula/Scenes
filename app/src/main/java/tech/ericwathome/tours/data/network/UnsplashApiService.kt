package tech.ericwathome.tours.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tech.ericwathome.tours.model.Photo

interface UnsplashApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") query: Int
    ): List<Photo>
}