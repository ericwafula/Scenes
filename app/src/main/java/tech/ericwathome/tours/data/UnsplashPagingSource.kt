package tech.ericwathome.tours.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.ericwathome.tours.data.network.UnsplashApiService
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.STARTING_PAGE
import tech.ericwathome.tours.util.TAG
import java.io.IOException

class UnsplashPagingSource (private val apiService: UnsplashApiService) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: STARTING_PAGE

        return try {
            val photos = apiService.getPhotos()

            return LoadResult.Page(
                data = photos,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}