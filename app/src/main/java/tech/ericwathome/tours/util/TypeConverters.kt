package tech.ericwathome.tours.util

import androidx.room.TypeConverter
import tech.ericwathome.tours.domain.model.Size
import tech.ericwathome.tours.domain.model.Sponsor
import tech.ericwathome.tours.domain.model.Sponsorship

class SizeConverter {
    @TypeConverter
    fun fromSizeToString(size: Size): String? {
        return size.regular
    }

    @TypeConverter
    fun fromStringToSize(value: String): Size {
        return Size(
            raw = null,
            full = null,
            regular = value,
            small = null,
            thumb = null
        )
    }
}

class SponsorshipConverter {
    @TypeConverter
    fun fromSponsorshipToString(sponsorship: Sponsorship?): String? {
        return sponsorship?.sponsor?.username
    }

    @TypeConverter
    fun fromStringToSponsorship(value: String?): Sponsorship {
        return Sponsorship(Sponsor(value))
    }
}