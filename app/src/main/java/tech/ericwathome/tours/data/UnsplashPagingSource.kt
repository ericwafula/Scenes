package tech.ericwathome.tours.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import tech.ericwathome.tours.data.network.UnsplashApiService
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.STARTING_PAGE
import java.io.IOException

class UnsplashPagingSource (private val apiService: UnsplashApiService) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: STARTING_PAGE

        return try {
            val photos = apiService.getPhotos(page)

            return LoadResult.Page(
                data = photos,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}