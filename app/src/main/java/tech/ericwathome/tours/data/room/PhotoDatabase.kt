package tech.ericwathome.tours.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.ericwathome.tours.model.Photo

@Database(
    entities = [Photo::class],
    version = 1,
    exportSchema = false
)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}