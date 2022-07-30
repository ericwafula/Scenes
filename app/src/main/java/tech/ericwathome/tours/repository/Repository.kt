package tech.ericwathome.tours.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.Resource

interface Repository {
    fun allPhotos(): Flow<PagingData<Photo>>
}