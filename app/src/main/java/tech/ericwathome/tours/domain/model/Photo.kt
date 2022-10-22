package tech.ericwathome.tours.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Photo(
    @PrimaryKey val id: String,
    val description: String?,
    val altDescription: String?,
    @SerializedName("urls") val size: Size?,
    val likes: Int,
    val sponsorship: Sponsorship?
)

data class Size(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
)

data class Sponsorship(
    val sponsor: Sponsor?
)

data class Sponsor(
    val username: String?
)