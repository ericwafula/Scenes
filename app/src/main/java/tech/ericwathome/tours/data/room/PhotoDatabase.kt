package tech.ericwathome.tours.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.SizeConverter
import tech.ericwathome.tours.util.SponsorshipConverter

@Database(
    entities = [Photo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SizeConverter::class, SponsorshipConverter::class)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}