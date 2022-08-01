package tech.ericwathome.tours.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tech.ericwathome.tours.model.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Query("SELECT * FROM photo")
    suspend fun savedPhotos(): List<Photo>

    @Delete
    suspend fun delete(photo: Photo)
}