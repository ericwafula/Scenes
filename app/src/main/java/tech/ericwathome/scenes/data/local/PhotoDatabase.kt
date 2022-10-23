package tech.ericwathome.scenes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.ericwathome.scenes.domain.model.Photo
import tech.ericwathome.scenes.util.SizeConverter
import tech.ericwathome.scenes.util.SponsorshipConverter

@Database(
    entities = [Photo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SizeConverter::class, SponsorshipConverter::class)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}