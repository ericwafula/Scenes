package tech.ericwathome.scenes.data.local

import androidx.room.*
import tech.ericwathome.scenes.domain.model.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Query("SELECT * FROM photo")
    suspend fun savedPhotos(): List<Photo>

    @Delete
    suspend fun delete(photo: Photo)
}