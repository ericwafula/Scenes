package tech.ericwathome.scenes.feature_scenes.data.data_source.local

import androidx.room.*
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Query("SELECT * FROM photo")
    suspend fun savedPhotos(): List<Photo>

    @Delete
    suspend fun delete(photo: Photo)
}