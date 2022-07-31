package tech.ericwathome.tours.util

import androidx.room.TypeConverter
import tech.ericwathome.tours.model.Size
import tech.ericwathome.tours.model.Sponsor
import tech.ericwathome.tours.model.Sponsorship

class SizeConverter {
    @TypeConverter
    fun fromSizeToString(size: Size): String? {
        return size.regular
    }

    @TypeConverter
    fun fromStringToSize(value: String): Size {
        return Size(value)
    }
}

class SponsorshipConverter {
    @TypeConverter
    fun fromSponsorshipToString(sponsorship: Sponsorship): String? {
        return sponsorship.sponsor?.username
    }

    @TypeConverter
    fun fromStringToSponsorship(value: String): Sponsorship {
        return Sponsorship(Sponsor(value))
    }
}