package tech.ericwathome.tours.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import tech.ericwathome.tours.domain.model.Photo
import tech.ericwathome.tours.domain.repository.Repository
import tech.ericwathome.tours.util.STARTING_PAGE
import java.io.IOException

class UnsplashPagingSource (private val repository: Repository) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: STARTING_PAGE

        return try {
            val photos = repository.allPhotos(page)

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