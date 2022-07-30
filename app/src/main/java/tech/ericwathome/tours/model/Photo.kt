package tech.ericwathome.tours.model

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: String,
    val description: String?,
    val altDescription: String?,
    @SerializedName("urls") val size: Size?
)

data class Size(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
)